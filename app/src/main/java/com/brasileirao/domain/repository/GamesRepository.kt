package com.brasileirao.domain.repository

import com.brasileirao.domain.model.Game
import io.reactivex.Flowable
import io.reactivex.Single

interface GamesRepository {
    fun get(): Flowable<List<Game>>
    fun getById(id: Long): Single<Game>
}