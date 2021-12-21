package com.brasileirao.data.repository

import com.brasileirao.data.database.dao.GameDao
import com.brasileirao.domain.repository.GamesRepository
import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class GamesRepositoryImplTest: KoinTest {

    @get:Rule
    val rule = KoinRule()

    private val repository: GamesRepository by inject()
    private val gameDao: GameDao by inject()

    @Test
    fun `get Should emit saved games first and then games from remote service`() {
        // Given
        val savedGame = games { expectedGames[0] }.copy(location = "Some location")
        val gamesFromRemote = games { expectedGames }
        gameDao.save(savedGame).test()

        // When
        val result = repository.get().test()

        // Then
        result.assertValues(listOf(savedGame), gamesFromRemote)
    }

    @Test
    fun `when no game is saved, get Should retrieve games from remote and save them locally`() {
        // Given
        val gamesFromRemote = games { expectedGames }

        // When
        repository.get().test()
        val result = gameDao.getAllGames().test()

        // Then
        result.assertValue { it == gamesFromRemote }
    }

    @Test
    fun `get Should clear old local games and save new games from remote`() {
        // Given
        generateOldGames()
            .onEach { gameDao.save(it).test() }

        // When
        repository.get().test()
        val result = gameDao.getAllGames().test()

        // Then
        result.assertValue { it == games { expectedGames } }
    }

    private fun generateOldGames() = games {
        expectedGames.map {
            it.copy(id = it.id + 30)
        }
    }
}