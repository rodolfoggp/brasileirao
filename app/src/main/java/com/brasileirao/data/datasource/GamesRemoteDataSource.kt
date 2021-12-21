package com.brasileirao.data.datasource

import com.brasileirao.domain.model.Game
import io.reactivex.Single

interface GamesRemoteDataSource {
    fun get(): Single<List<Game>>
}