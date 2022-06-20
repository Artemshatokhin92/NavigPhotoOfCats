package com.shatokhin.navigphotoofcats.data.room.dao

import androidx.room.*
import com.shatokhin.navigphotoofcats.data.room.AppDatabase.Companion.NAME_TABLE_CATS_FAVORITE
import com.shatokhin.navigphotoofcats.data.room.entity.CatFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsFavoriteDao {

    // region Insert, Update, Delete - работают по PrimaryKey
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCats(listTag: List<CatFavorite>)
    @Update
    suspend fun updateCats(listTag: List<CatFavorite>)
    @Delete
    suspend fun deleteCats(listTag: List<CatFavorite>)
    // endregion

    @Query("SELECT * FROM $NAME_TABLE_CATS_FAVORITE")
    fun getAllCats(): Flow<List<CatFavorite>>

    @Query("SELECT * FROM $NAME_TABLE_CATS_FAVORITE WHERE id LIKE :id")
    fun getCatByIdServer(id: String): Flow<List<CatFavorite>>
}