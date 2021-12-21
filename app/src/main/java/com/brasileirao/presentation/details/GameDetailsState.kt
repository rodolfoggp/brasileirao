package com.brasileirao.presentation.details

import com.brasileirao.domain.model.Game


data class GameDetailsState(
    val game: Game? = null,
) {
    companion object {
        val initialState = GameDetailsState()
    }
}
