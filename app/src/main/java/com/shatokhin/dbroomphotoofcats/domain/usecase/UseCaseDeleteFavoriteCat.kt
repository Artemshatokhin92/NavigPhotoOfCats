package com.shatokhin.dbroomphotoofcats.domain.usecase

import com.shatokhin.dbroomphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.dbroomphotoofcats.domain.repository.Repository

class UseCaseDeleteFavoriteCat(private val repository: Repository) {
    suspend fun execute(cat: CatFavorite) {
        return repository.deleteFavoriteCat(cat)
    }
}