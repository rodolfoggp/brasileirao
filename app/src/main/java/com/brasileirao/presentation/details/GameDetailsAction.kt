package com.brasileirao.presentation.details

sealed class GameDetailsAction {
    data class DisplayErrorDialog(val message: String): GameDetailsAction()
}