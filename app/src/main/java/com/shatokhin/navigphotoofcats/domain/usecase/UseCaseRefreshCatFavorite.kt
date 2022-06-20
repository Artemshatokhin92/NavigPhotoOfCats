package com.shatokhin.navigphotoofcats.domain.usecase

import com.shatokhin.navigphotoofcats.domain.repository.Repository

class UseCaseRefreshCatFavorite(private val repository: Repository) {

    suspend fun execute(){
        repository.refreshCatFavorite()
    }

}