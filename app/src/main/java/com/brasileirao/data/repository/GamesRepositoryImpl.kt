package com.brasileirao.data.repository

import com.brasileirao.data.datasource.GamesLocalDataSource
import com.brasileirao.data.datasource.GamesRemoteDataSource
import com.brasileirao.domain.exception.GameNotFoundException
import com.brasileirao.domain.model.Game
import com.brasileirao.domain.repository.GamesRepository
import io.reactivex.Flowable
import io.reactivex.Single

class GamesRepositoryImpl(
    private val localDataSource: GamesLocalDataSource,
    private val remoteDataSource: GamesRemoteDataSource,
) : GamesRepository {

    override fun get(): Flowable<List<Game>> = localDataSource.get()
        .concatWith(getFromRemote())

    private fun getFromRemote(): Single<List<Game>> =
        remoteDataSource.get()
            .flatMap { games ->
                localDataSource.clear()
                    .andThen(localDataSource.save(games))
                    .andThen(Single.just(games))
            }

    override fun getById(id: Long) = localDataSource.get()
        .map { games -> games.findBy(id) }

    private fun List<Game>.findBy(id: Long) =
        find { it.id == id } ?: throw GameNotFoundException()
}
