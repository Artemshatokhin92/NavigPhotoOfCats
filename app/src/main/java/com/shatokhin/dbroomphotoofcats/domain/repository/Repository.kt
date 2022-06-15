package com.shatokhin.dbroomphotoofcats.domain.repository

import com.shatokhin.dbroomphotoofcats.data.models.Cat
import com.shatokhin.dbroomphotoofcats.data.models.ResultDeleteVote
import com.shatokhin.dbroomphotoofcats.data.models.ResultPostVote
import com.shatokhin.dbroomphotoofcats.data.models.Vote
import com.shatokhin.dbroomphotoofcats.data.room.entity.CatFavorite
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getRandomCats(count: Int): List<Cat>?
    suspend fun voteUpCat(cat: Cat): ResultPostVote
    suspend fun voteDownCat(cat: Cat): ResultPostVote
    suspend fun getVotes(): List<Vote>?
    suspend fun deleteVote(idVote: Int): ResultDeleteVote
    suspend fun getCatById(idCat: String): Cat?
    fun getFavoriteCats(): Flow<List<CatFavorite>>
    suspend fun refreshCatFavorite()
    suspend fun deleteFavoriteCat(cat: CatFavorite)
}