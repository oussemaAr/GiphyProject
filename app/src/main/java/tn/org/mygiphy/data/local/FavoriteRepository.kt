package tn.org.mygiphy.data.local

import tn.org.mygiphy.data.local.dao.FavoriteDao
import tn.org.mygiphy.data.local.entity.GifEntity
import tn.org.mygiphy.model.GifItem

class FavoriteRepository(private val dao: FavoriteDao) {

    suspend fun addFavorite(item: GifItem): Long {
        return if (dao.getItemById(item.id) == null) {
            val entity = GifEntity(
                id = item.id,
                title = item.title,
                url = item.url
            )
            dao.insertGif(entity)
        } else {
            dao.deleteFavorite(item.id)
            -1
        }
    }

    suspend fun isFavorite(id: String) = dao.getItemById(id)

    fun loadFavorites() =
        dao.getAllFavorites()

    suspend fun delete(item: GifItem) {
        dao.deleteFavorite(item.id)
    }

}