package com.shatokhin.dbroomphotoofcats.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shatokhin.dbroomphotoofcats.data.room.dao.CatsFavoriteDao
import com.shatokhin.dbroomphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.dbroomphotoofcats.utilites.DATABASE_NAME

@Database(entities = [CatFavorite::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catFavoriteDao(): CatsFavoriteDao

    companion object {
        const val NAME_TABLE_CATS_FAVORITE = "CatsFavorite"

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // builder AppDataBase (само создание базы данных)
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//                .fallbackToDestructiveMigration()
                .build()
        }

    }

}