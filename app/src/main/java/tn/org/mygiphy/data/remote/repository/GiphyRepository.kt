package tn.org.mygiphy.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tn.org.mygiphy.PAGE_SIZE
import tn.org.mygiphy.data.local.FavoriteRepository
import tn.org.mygiphy.data.remote.api.GiphyService
import tn.org.mygiphy.model.GifItem

class GiphyRepository(
    private val service: GiphyService,
    private val repository: FavoriteRepository
) {
    fun getSearchResult(query: String): Flow<PagingData<GifItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GiphyPagingSource(
                    query,
                    service,
                    repository
                )
            }
        ).flow
    }

}