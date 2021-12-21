package com.brasileirao.data.api.mapper

import com.brasileirao.data.api.model.GamesResponse
import com.brasileirao.domain.model.Game

fun GamesResponse.toGames() =
    games.map { element ->
        with(element) {
            Game(
                id = id,
                team1Performance = team1Performance,
                team2Performance = team2Performance,
                dateTime = dateTime,
                location = location,
                highlights = highlights,
            )
        }
    }