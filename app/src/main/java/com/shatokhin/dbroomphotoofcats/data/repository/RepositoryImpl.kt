package com.shatokhin.dbroomphotoofcats.data.repository

import android.util.Log
import com.shatokhin.dbroomphotoofcats.App
import com.shatokhin.dbroomphotoofcats.data.models.*
import com.shatokhin.dbroomphotoofcats.data.network.NetworkHttp
import com.shatokhin.dbroomphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.dbroomphotoofcats.domain.repository.Repository
import com.shatokhin.dbroomphotoofcats.utilites.TAG_FOR_LOGCAT
import kotlinx.coroutines.flow.Flow

class RepositoryImpl: Repository {
    private val network = NetworkHttp()
    private val room = App.instance.catsFavoriteDao

    override suspend fun getRandomCats(count: Int): List<Cat>? {
        return network.getRandomCats(count)
    }

    override suspend fun voteUpCat(cat: Cat): ResultPostVote {
        return network.voteUpCat(cat)
    }

    override suspend fun voteDownCat(cat: Cat): ResultPostVote {
        return network.voteDownCat(cat)
    }

    override suspend fun deleteVote(idVote: Int): ResultDeleteVote {
        return network.deleteVote(idVote)
    }

    override suspend fun getVotes(): List<Vote>? {
        return network.getVotes()
    }

    override suspend fun getCatById(idCat: String): Cat? {
        return network.getCatById(idCat)
    }

    override fun getFavoriteCats(): Flow<List<CatFavorite>> {
        return room.getAllCats()
    }

    override suspend fun refreshCatFavorite(){
        val catFavoriteFromServer = mutableListOf<CatFavorite>()
        val listVotes = getVotes()?: return

        listVotes.forEach { vote ->
            if (vote.value == 1) {
                val cat = getCatById(vote.idCat)
                if (cat != null){
                    catFavoriteFromServer.add(CatFavorite(id = cat.id, urlImage = cat.urlImage, idVote = vote.id))
                }
                else {
                    Log.d(TAG_FOR_LOGCAT, "Ошибка загрузки")
                }
            }
        }
        room.insertCats(catFavoriteFromServer)
    }

    override suspend fun deleteFavoriteCat(cat: CatFavorite) {
        room.deleteCats(listOf(cat))
    }

}