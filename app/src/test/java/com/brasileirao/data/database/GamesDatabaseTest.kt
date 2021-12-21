package com.brasileirao.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.brasileirao.data.database.dao.GameDao
import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import com.example.testing_library.rule.SyncTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GamesDatabaseTest : KoinTest {

    @get:Rule
    val rule = KoinRule()

    @get:Rule
    val syncTestRule = SyncTestRule()

    private lateinit var gameDao: GameDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val database = Room.inMemoryDatabaseBuilder(context, GamesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        gameDao = database.getGameDao()
    }

    @Test
    fun `when a game is saved, getAllGames Should return a list with it`() {
        // Given
        val game = games { expectedGames[0] }

        // When
        gameDao.save(game).test()

        // Then
        val result = gameDao.getAllGames().test()
        result.assertValue { it == listOf(game) }
    }

    @Test
    fun `getGameById Should return game with desired id`() {
        // Given
        val games = games { expectedGames }
            .onEach { gameDao.save(it).test() }
        val expectedGame = games[0]

        // When
        val result = gameDao.getGameById(expectedGame.id).test()

        // Then
        result.assertValue { it == expectedGame }
    }

    @Test
    fun `clear Should delete all games and highlights on database`() {
        // Given
        games { expectedGames }
            .onEach { gameDao.save(it).test() }

        // When
        gameDao.clear().test()

        // Then
        val result = gameDao.getAllGames().test()
        result.assertValue { it.isEmpty() }
    }
}