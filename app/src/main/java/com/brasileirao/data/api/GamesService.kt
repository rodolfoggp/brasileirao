package com.brasileirao.data.api

import com.brasileirao.data.api.model.GamesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GamesService {
    @GET("/games")
    fun getGames(): Single<GamesResponse>
}
