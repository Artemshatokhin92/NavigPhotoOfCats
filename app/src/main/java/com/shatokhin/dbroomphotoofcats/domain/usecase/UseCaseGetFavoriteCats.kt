package com.shatokhin.dbroomphotoofcats.domain.usecase

import com.shatokhin.dbroomphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.dbroomphotoofcats.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class UseCaseGetFavoriteCats(private val repository: Repository) {

    fun execute(): Flow<List<CatFavorite>> {
        return repository.getFavoriteCats()
    }

}