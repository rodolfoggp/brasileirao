package com.brasileirao.data.datasource

import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class GamesServiceDataSourceTest: KoinTest {
    @get:Rule
    val rule = KoinRule()

    private val dataSource: GamesRemoteDataSource by inject()

    @Test
    fun `method get Should get games from service`() {
        // Given
        val expectedGames = games { expectedGames }

        // When
        val result = dataSource.get().test()

        // Then
        result.assertValue { it == expectedGames }
    }
}