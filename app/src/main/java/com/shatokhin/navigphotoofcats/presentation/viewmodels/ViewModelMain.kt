package com.shatokhin.navigphotoofcats.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shatokhin.navigphotoofcats.data.models.Cat
import com.shatokhin.navigphotoofcats.data.room.entity.CatFavorite
import com.shatokhin.navigphotoofcats.domain.usecase.*
import com.shatokhin.navigphotoofcats.utilites.TEXT_INTERNET_RESUMED
import com.shatokhin.navigphotoofcats.utilites.TEXT_RESPONSE_NETWORK_ERROR
import com.shatokhin.navigphotoofcats.utilites.TEXT_RESPONSE_NETWORK_SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ViewModelMain(
    private val useCaseGetRandomCats: UseCaseGetRandomCats,
    private val useCaseVoteUpCat: UseCaseVoteUpCat,
    private val useCaseDeleteVote: UseCaseDeleteVote,
    private val useCaseGetFavoriteCats: UseCaseGetFavoriteCats,
    private val useCaseRefreshCatFavorite: UseCaseRefreshCatFavorite,
    private val useCaseDeleteFavoriteCat: UseCaseDeleteFavoriteCat,
) : ViewModel() {
    init {
        refreshCatFavoriteInRoom()
    }

    private val mListCats = MutableLiveData<Cat>()
    val listCats: LiveData<Cat>
        get() = mListCats

    private val mErrorNetwork = MutableLiveData<String>()
    val errorNetwork: LiveData<String>
        get() = mErrorNetwork

    val listFavoriteCats: Flow<List<CatFavorite>> = useCaseGetFavoriteCats.execute()

    fun loadRandomCats() {
        viewModelScope.launch {
            val newRandomCat: Cat? = useCaseGetRandomCats.execute()
            if (newRandomCat == null) mErrorNetwork.value = TEXT_RESPONSE_NETWORK_ERROR
            newRandomCat?.let {
                mListCats.value = it
            }
        }
    }

    fun voteUpCurrentCat() {
        viewModelScope.launch {
            mListCats.value?.let { currentCat ->
                val response = useCaseVoteUpCat.execute(currentCat)
                when(response.message){
                    TEXT_RESPONSE_NETWORK_ERROR -> mErrorNetwork.value = TEXT_RESPONSE_NETWORK_ERROR
                    TEXT_RESPONSE_NETWORK_SUCCESS -> {
                        loadRandomCats()
                        refreshCatFavoriteInRoom()
                    }
                }
            }
        }
    }

    private fun refreshCatFavoriteInRoom(){
        viewModelScope.launch {
            useCaseRefreshCatFavorite.execute()
        }
    }

    fun voteDownCurrentCat() {
        loadRandomCats()
    }

    fun deleteFavoriteCat(cat: CatFavorite) {
        viewModelScope.launch {
            val response = useCaseDeleteVote.execute(cat.idVote)
            when(response.message){
                TEXT_RESPONSE_NETWORK_ERROR -> mErrorNetwork.value = TEXT_RESPONSE_NETWORK_ERROR
                TEXT_RESPONSE_NETWORK_SUCCESS -> useCaseDeleteFavoriteCat.execute(cat)
            }
        }
    }

    fun internetConnectionResumed() {
        viewModelScope.launch(Dispatchers.Main) {
            if (mErrorNetwork.value == TEXT_RESPONSE_NETWORK_ERROR) mErrorNetwork.value = TEXT_INTERNET_RESUMED
        }
        if (mListCats.value == null) loadRandomCats()
    }

}