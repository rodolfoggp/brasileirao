package com.brasileirao.data.database.dao

import com.brasileirao.data.database.entity.GameEntity
import com.brasileirao.data.database.entity.GameWithHighlights
import com.brasileirao.data.database.entity.HighlightEntity
import com.brasileirao.data.database.mapper.toEntity
import com.brasileirao.domain.model.Game
import com.brasileirao.robot.games
import io.reactivex.Completable
import io.reactivex.Single

class FakeGameDao : GameDao() {
    val games = mutableMapOf<Long, Game>()

    override fun save(game: Game) = Completable.fromCallable {
        games.put(game.id, game)
    }

    override fun getAllGames(): Single<List<Game>> = Single.just(games.values.toList())

    override fun getGameWithHighlightsById(id: Long): Single<GameWithHighlights> {
        val exampleGame = games { expectedGames[0] }
        return Single.just(GameWithHighlights(exampleGame.toEntity(),
            exampleGame.highlights.toEntity(exampleGame.id)))
    }

    override fun save(game: GameEntity): Single<Long> = Single.just(0)

    override fun save(highlights: List<HighlightEntity>) = Completable.complete()

    override fun getAllGamesWithHighlights() = Single.just(listOf<GameWithHighlights>())

    override fun clearHighlights() = Completable.complete()

    override fun clearGames() = Completable.complete()

    override fun clear() = Completable.fromCallable { games.clear() }
}
