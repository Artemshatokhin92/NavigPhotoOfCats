package com.shatokhin.navigphotoofcats

import android.app.Application
import com.shatokhin.navigphotoofcats.data.room.AppDatabase
import com.shatokhin.navigphotoofcats.data.room.dao.CatsFavoriteDao

class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var catsFavoriteDao: CatsFavoriteDao

    override fun onCreate() {
        super.onCreate()

        instance = this

        initCatsFavoriteDao()
    }

    private fun initCatsFavoriteDao() {
        catsFavoriteDao = AppDatabase.getInstance(applicationContext).catFavoriteDao()
    }

}