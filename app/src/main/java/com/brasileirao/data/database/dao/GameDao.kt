package com.brasileirao.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.brasileirao.data.database.entity.GameEntity
import com.brasileirao.data.database.entity.GameWithHighlights
import com.brasileirao.data.database.entity.HighlightEntity
import com.brasileirao.data.database.mapper.toEntity
import com.brasileirao.data.database.mapper.toModel
import com.brasileirao.domain.model.Game
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class GameDao {
    @Insert(onConflict = REPLACE)
    protected abstract fun save(game: GameEntity): Single<Long>

    @Insert(onConflict = REPLACE)
    protected abstract fun save(highlights: List<HighlightEntity>): Completable

    open fun save(game: Game): Completable =
        save(game.toEntity())
            .flatMapCompletable { savedGameId ->
                save(game.highlights.toEntity(savedGameId))
            }

    @Query("SELECT * FROM games")
    protected abstract fun getAllGamesWithHighlights(): Single<List<GameWithHighlights>>

    open fun getAllGames(): Single<List<Game>> =
        getAllGamesWithHighlights()
            .map { gamesWithHighlights ->
                gamesWithHighlights.map { it.toModel() }
            }

    @Query("SELECT * FROM games WHERE id = :id")
    protected abstract fun getGameWithHighlightsById(id: Long): Single<GameWithHighlights>

    fun getGameById(id: Long) =
        getGameWithHighlightsById(id)
            .map { it.toModel() }

    @Query("DELETE FROM highlights")
    protected abstract fun clearHighlights(): Completable

    @Query("DELETE FROM games")
    protected abstract fun clearGames(): Completable

    open fun clear(): Completable = clearHighlights().andThen(clearGames())
}
