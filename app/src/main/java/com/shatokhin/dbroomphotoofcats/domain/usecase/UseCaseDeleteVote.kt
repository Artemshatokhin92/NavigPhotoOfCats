package com.shatokhin.dbroomphotoofcats.domain.usecase

import com.shatokhin.dbroomphotoofcats.data.models.ResultDeleteVote
import com.shatokhin.dbroomphotoofcats.domain.repository.Repository

class UseCaseDeleteVote(private val repository: Repository) {

    suspend fun execute(idVote: Int): ResultDeleteVote {
        return repository.deleteVote(idVote)
    }

}