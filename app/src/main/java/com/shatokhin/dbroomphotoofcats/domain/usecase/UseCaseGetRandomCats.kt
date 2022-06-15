package com.shatokhin.dbroomphotoofcats.domain.usecase

import com.shatokhin.dbroomphotoofcats.data.models.Cat
import com.shatokhin.dbroomphotoofcats.domain.repository.Repository

class UseCaseGetRandomCats(private val repository: Repository) {

    suspend fun execute(): Cat? {
        val listCat = repository.getRandomCats(1) ?: return null
        val cat = listCat[0]
        return cat
    }

}