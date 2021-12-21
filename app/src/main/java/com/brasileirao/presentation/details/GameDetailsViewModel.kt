package com.brasileirao.presentation.details

import com.brasileirao.R
import com.brasileirao.domain.model.Game
import com.brasileirao.domain.repository.GamesRepository
import com.brasileirao.presentation.details.GameDetailsAction.DisplayErrorDialog
import com.core.resource.ResourceProvider
import com.core.viewmodel.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameDetailsViewModel(
    private val gameId: Long,
    private val gamesRepository: GamesRepository,
    private val resourceProvider: ResourceProvider,
) : ViewModel<GameDetailsState, GameDetailsAction>(GameDetailsState.initialState) {

    fun getGame() {
        gamesRepository.getById(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ game -> onGameGotten(game) }, { onErrorGettingGame() })
            .handleDisposable()
    }

    private fun onGameGotten(game: Game) {
        setState { it.copy(game = game) }
    }

    private fun onErrorGettingGame() {
        sendAction {
            DisplayErrorDialog(resourceProvider.getString(R.string.error_finding_game_message))
        }
    }
}