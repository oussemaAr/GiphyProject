package tn.org.mygiphy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tn.org.mygiphy.data.local.entity.GifEntity


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGif(item: GifEntity): Long

    @Query("SELECT * FROM favorite_table ORDER BY added_at")
    fun getAllFavorites(): Flow<List<GifEntity>>

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    suspend fun getItemById(id: String): GifEntity?

    @Query("DELETE FROM favorite_table WHERE id like :id")
    suspend fun deleteFavorite(id: String)

}