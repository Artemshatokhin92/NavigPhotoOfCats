package com.shatokhin.dbroomphotoofcats.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shatokhin.dbroomphotoofcats.data.repository.RepositoryImpl
import com.shatokhin.dbroomphotoofcats.domain.usecase.*

class ViewModelMainFactory: ViewModelProvider.Factory {
    private val repositoryImpl = RepositoryImpl()
    private val useCaseGetRandomCats = UseCaseGetRandomCats(repositoryImpl)
    private val useCaseVoteUpCat = UseCaseVoteUpCat(repositoryImpl)
    private val useCaseDeleteVote = UseCaseDeleteVote(repositoryImpl)
    private val useCaseGetFavoriteCats = UseCaseGetFavoriteCats(repositoryImpl)
    private val useCaseRefreshCatFavorite = UseCaseRefreshCatFavorite(repositoryImpl)
    private val useCaseDeleteFavoriteCat = UseCaseDeleteFavoriteCat(repositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelMain(
            useCaseDeleteVote = useCaseDeleteVote,
            useCaseGetRandomCats = useCaseGetRandomCats,
            useCaseVoteUpCat = useCaseVoteUpCat,
            useCaseGetFavoriteCats = useCaseGetFavoriteCats,
            useCaseRefreshCatFavorite = useCaseRefreshCatFavorite,
            useCaseDeleteFavoriteCat = useCaseDeleteFavoriteCat
        ) as T
    }

}