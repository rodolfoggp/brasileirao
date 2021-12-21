package com.brasileirao.data.datasource

import com.brasileirao.domain.model.Game
import io.reactivex.Completable
import io.reactivex.Single

interface GamesLocalDataSource {
    fun get(): Single<List<Game>>
    fun getById(id: Long): Single<Game>
    fun save(games: List<Game>): Completable
    fun clear(): Completable
}