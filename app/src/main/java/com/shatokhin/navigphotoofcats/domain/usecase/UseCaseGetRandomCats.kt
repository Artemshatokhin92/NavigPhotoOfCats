package com.shatokhin.navigphotoofcats.domain.usecase

import com.shatokhin.navigphotoofcats.data.models.Cat
import com.shatokhin.navigphotoofcats.domain.repository.Repository

class UseCaseGetRandomCats(private val repository: Repository) {

    suspend fun execute(): Cat? {
        val listCat = repository.getRandomCats(1) ?: return null
        val cat = listCat[0]
        return cat
    }

}