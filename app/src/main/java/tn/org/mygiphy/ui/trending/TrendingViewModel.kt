package tn.org.mygiphy.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import tn.org.mygiphy.data.local.FavoriteRepository
import tn.org.mygiphy.data.remote.repository.GiphyRepository
import tn.org.mygiphy.model.GifItem
import javax.inject.Inject


@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository,
    private val favoriteRepository: FavoriteRepository
) :
    ViewModel() {

    fun search(query: String) =
        giphyRepository
            .getSearchResult(query)
            .cachedIn(viewModelScope)

    fun favorites(item: GifItem) = flow {
        emit(favoriteRepository.addFavorite(item))
    }

}
