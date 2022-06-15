package com.shatokhin.dbroomphotoofcats.domain.usecase

import com.shatokhin.dbroomphotoofcats.domain.repository.Repository

class UseCaseRefreshCatFavorite(private val repository: Repository) {

    suspend fun execute(){
        repository.refreshCatFavorite()
    }

}