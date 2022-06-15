package com.shatokhin.dbroomphotoofcats.domain.usecase

import com.shatokhin.dbroomphotoofcats.data.models.Cat
import com.shatokhin.dbroomphotoofcats.data.models.ResultPostVote
import com.shatokhin.dbroomphotoofcats.domain.repository.Repository

class UseCaseVoteUpCat(private val repository: Repository) {

    suspend fun execute(cat: Cat): ResultPostVote {
        return repository.voteUpCat(cat)
    }

}