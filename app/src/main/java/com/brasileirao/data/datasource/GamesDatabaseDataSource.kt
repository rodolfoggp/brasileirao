package com.brasileirao.data.datasource

import com.brasileirao.data.database.dao.GameDao
import com.brasileirao.domain.model.Game
import io.reactivex.Completable
import io.reactivex.Single

class GamesDatabaseDataSource(
    private val gameDao: GameDao,
) : GamesLocalDataSource {
    override fun get(): Single<List<Game>> = gameDao.getAllGames()

    override fun getById(id: Long): Single<Game> = gameDao.getGameById(id)

    override fun save(games: List<Game>): Completable =
        Completable.concat(games.map { gameDao.save(it) })

    override fun clear() = gameDao.clear()
}
