package com.brasileirao.data.api

import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.KoinTest

class GamesServiceTest: KoinTest {

    @get:Rule
    val rule = KoinRule()

    private val service: GamesService by inject()
    private val gson: Gson by inject()

    @Test
    fun `getGames Should get games from api`() {
        // Given
        val response = games { gamesResponseFromServer }

        // When
        val result = service.getGames().test()

        // Then
        result.assertValue { it == response }
    }
}