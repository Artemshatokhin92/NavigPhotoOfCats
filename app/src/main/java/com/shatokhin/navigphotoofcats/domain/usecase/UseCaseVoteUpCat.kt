package com.shatokhin.navigphotoofcats.domain.usecase

import com.shatokhin.navigphotoofcats.data.models.Cat
import com.shatokhin.navigphotoofcats.data.models.ResultPostVote
import com.shatokhin.navigphotoofcats.domain.repository.Repository

class UseCaseVoteUpCat(private val repository: Repository) {

    suspend fun execute(cat: Cat): ResultPostVote {
        return repository.voteUpCat(cat)
    }

}