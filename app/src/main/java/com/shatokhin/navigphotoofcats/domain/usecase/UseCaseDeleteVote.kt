package com.shatokhin.navigphotoofcats.domain.usecase

import com.shatokhin.navigphotoofcats.data.models.ResultDeleteVote
import com.shatokhin.navigphotoofcats.domain.repository.Repository

class UseCaseDeleteVote(private val repository: Repository) {

    suspend fun execute(idVote: Int): ResultDeleteVote {
        return repository.deleteVote(idVote)
    }

}