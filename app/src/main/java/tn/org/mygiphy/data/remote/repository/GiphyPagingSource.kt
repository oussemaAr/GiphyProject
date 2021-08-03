package tn.org.mygiphy.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import tn.org.mygiphy.PAGE_SIZE
import tn.org.mygiphy.STARTING_PAGE_INDEX
import tn.org.mygiphy.data.local.FavoriteRepository
import tn.org.mygiphy.data.remote.api.GiphyService
import tn.org.mygiphy.model.GifItem
import tn.org.mygiphy.utils.asGifItemList

class GiphyPagingSource(
    private val query: String,
    private val giphyService: GiphyService,
    private val repository: FavoriteRepository
) :
    PagingSource<Int, GifItem>() {

    override fun getRefreshKey(state: PagingState<Int, GifItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                if (query.isEmpty()) giphyService.trendingGif() else giphyService.searchGif(
                    query = query,
                    offset = position
                )
            if ((response.meta.status != 200) or (response.pagination.totalCount == 0)) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                    nextKey = null
                )
            }
            val data = response.asGifItemList().map {
                if (repository.isFavorite(it.id) != null)
                    it.isFavorite = true
                it
            }
            val offset = if (data.isEmpty()) null else position + (params.loadSize / PAGE_SIZE)
            LoadResult.Page(
                data = data,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                nextKey = offset
            )
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}