package com.brasileirao.presentation.list

import com.brasileirao.R
import com.brasileirao.domain.model.Game
import com.brasileirao.domain.repository.GamesRepository
import com.brasileirao.presentation.list.GamesListAction.DisplayErrorDialog
import com.brasileirao.presentation.list.GamesListAction.StopRefreshing
import com.core.resource.ResourceProvider
import com.core.viewmodel.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GamesListViewModel(
    private val repository: GamesRepository,
    private val resourceProvider: ResourceProvider,
) : ViewModel<GamesListState, GamesListAction>(GamesListState.initialState) {

    fun getGames() {
        repository.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { hideRefresh() }
            .subscribe({ games -> onGamesReceived(games) }, { onErrorGettingGames() })
            .handleDisposable()
    }

    private fun hideRefresh() {
        sendAction { StopRefreshing }
    }

    private fun onGamesReceived(games: List<Game>) {
        setState { it.copy(games = games) }
    }

    private fun onErrorGettingGames() {
        sendAction { DisplayErrorDialog(resourceProvider.getString(R.string.error_getting_games_message)) }
    }
}