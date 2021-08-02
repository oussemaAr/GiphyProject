package tn.org.mygiphy.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.org.mygiphy.data.remote.repository.GiphyRepository
import javax.inject.Inject


@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository
) :
    ViewModel() {

    fun search(query: String) =
        giphyRepository
            .getSearchResult(query)
            .cachedIn(viewModelScope)

}
