package com.brasileirao.data.datasource

import com.brasileirao.data.database.dao.GameDao
import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class GamesDatabaseDataSourceTest : KoinTest {

    @get:Rule
    val rule = KoinRule()

    private val dataSource: GamesLocalDataSource by inject()
    private val dao: GameDao by inject()

    @Test
    fun `get Should retrieve saved games from database`() {
        // Given
        val games = games { expectedGames }
            .onEach { dao.save(it).test() }

        // When
        val result = dataSource.get().test()

        // Then
        result.assertValue { it == games }
    }

    @Test
    fun `getById Should return the game with desired id`() {
        // Given
        val games = games { expectedGames }
            .onEach { dao.save(it).test() }
        val expectedGame = games[0]

        // When
        val result = dataSource.getById(expectedGame.id).test()

        // Then
        result.assertValue { it == expectedGame }
    }

    @Test
    fun `save Should store all games in database`() {
        // Given
        val games = games { expectedGames }

        // When
        dataSource.save(games).test()

        // Then
        val result = dao.getAllGames().test()
        result.assertValue { it == games }
    }

    @Test
    fun `clear Should delete all games in database`() {
        // Given
        val games = games { expectedGames }
        dataSource.save(games).test()

        // When
        dataSource.clear().test()

        // Then
        val result = dao.getAllGames().test()
        result.assertValue { it.isEmpty() }
    }
}