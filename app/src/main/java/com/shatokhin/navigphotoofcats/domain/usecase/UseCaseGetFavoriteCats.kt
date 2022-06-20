package com.shatokhin.navigphotoofcats.domain.usecase

import com.shatokhin.navigphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.navigphotoofcats.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class UseCaseGetFavoriteCats(private val repository: Repository) {

    fun execute(): Flow<List<CatFavorite>> {
        return repository.getFavoriteCats()
    }

}