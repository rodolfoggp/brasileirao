package com.brasileirao.data.api

import com.brasileirao.data.api.model.GamesResponse
import com.google.gson.Gson
import io.reactivex.Single

class TestGamesService(
    gson: Gson,
): FakeGamesService(gson) {

    var shouldReturnError = false

    fun returnsError() {
        shouldReturnError = true
    }

    override fun getGames(): Single<GamesResponse> {
        return if (shouldReturnError){
            Single.error(Throwable("Error getting games"))
        } else {
            super.getGames()
        }
    }
}