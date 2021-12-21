package com.brasileirao.data.api

import com.brasileirao.data.api.model.GamesResponse
import com.core.file.ResourcesFileReader
import com.google.gson.Gson
import io.reactivex.Single

internal const val RESPONSE_PATH = "games_api_response"

open class FakeGamesService(
    val gson: Gson,
) : GamesService {

    override fun getGames(): Single<GamesResponse> {
        val json = readJsonFromFile()
        val response = gson.fromJson(json, GamesResponse::class.java)
        return Single.just(response)
    }

    private fun readJsonFromFile() =
        ResourcesFileReader.readFrom(RESPONSE_PATH)
}
