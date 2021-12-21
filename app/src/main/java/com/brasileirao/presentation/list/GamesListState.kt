package com.brasileirao.presentation.list

import com.brasileirao.domain.model.Game

data class GamesListState(
    val games: List<Game> = listOf(),
) {
    companion object {
        val initialState = GamesListState()
    }
}
