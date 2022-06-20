package com.shatokhin.navigphotoofcats.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shatokhin.navigphotoofcats.data.room.AppDatabase

@Entity(tableName = AppDatabase.NAME_TABLE_CATS_FAVORITE)
data class CatFavorite(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val urlImage: String,
    @ColumnInfo val idVote: Int,
)
