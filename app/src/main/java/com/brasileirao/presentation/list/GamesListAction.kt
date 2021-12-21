package com.brasileirao.presentation.list

sealed class GamesListAction {
    object StopRefreshing: GamesListAction()
    data class DisplayErrorDialog(val message: String): GamesListAction()
}
