package tn.org.mygiphy.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tn.org.mygiphy.data.local.FavoriteRepository
import tn.org.mygiphy.model.GifItem
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    fun loadFavorite() = favoriteRepository.loadFavorites().map {
        it.map { entity ->
            GifItem(
                id = entity.id,
                title = entity.title,
                url = entity.url
            )
        }
    }

    fun delete(item: GifItem) {
        viewModelScope.launch {
            favoriteRepository.delete(item)
        }
    }
}