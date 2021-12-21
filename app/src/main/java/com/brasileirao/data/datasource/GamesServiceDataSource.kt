package com.brasileirao.data.datasource

import com.brasileirao.data.api.GamesService
import com.brasileirao.data.api.mapper.toGames

class GamesServiceDataSource(
    private val service: GamesService,
) : GamesRemoteDataSource {
    override fun get() = service.getGames().map { response -> response.toGames() }
}