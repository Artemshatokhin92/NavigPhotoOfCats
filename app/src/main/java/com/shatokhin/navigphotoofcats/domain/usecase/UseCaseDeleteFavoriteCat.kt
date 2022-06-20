package com.shatokhin.navigphotoofcats.domain.usecase

import com.shatokhin.navigphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.navigphotoofcats.domain.repository.Repository

class UseCaseDeleteFavoriteCat(private val repository: Repository) {
    suspend fun execute(cat: CatFavorite) {
        return repository.deleteFavoriteCat(cat)
    }
}