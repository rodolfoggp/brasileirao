package com.brasileirao.presentation.details

import androidx.lifecycle.Observer
import com.brasileirao.data.datasource.GamesLocalDataSource
import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import com.core.resource.ResourceProvider
import com.example.testing_library.rule.SyncTestRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

class GameDetailsViewModelTest: KoinTest {

    @get:Rule
    val koinRule = KoinRule()

    @get:Rule
    val syncRule = SyncTestRule()

    private val stateObserver = mockk<Observer<GameDetailsState>>(relaxed = true)
    private val actionObserver = mockk<Observer<GameDetailsAction>>(relaxed = true)
    private val resourceProvider: ResourceProvider by inject()
    private val gamesLocalDataSource: GamesLocalDataSource by inject()

    @Test
    fun `getGame Should set the correct game in state`() {
        // Given
        val gameId: Long = 2
        val viewModel = createViewModel(gameId)

        val expectedGame = games { expectedGames }
            .also { gamesLocalDataSource.save(it).test() }
            .find { it.id == gameId }
        val expectedState = GameDetailsState(expectedGame)


        // When
        viewModel.getGame()

        // Then
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun `getGame with nonexistent id Should send a DisplayErrorDialog action`() {
        // Given
        val gameId: Long = 99
        val viewModel = createViewModel(gameId)
        val errorMessage = "Error message"
        every { resourceProvider.getString(any()) } returns errorMessage

        // When
        viewModel.getGame()

        // Then
        verify { actionObserver.onChanged(GameDetailsAction.DisplayErrorDialog(errorMessage)) }
    }

    private fun createViewModel(gameId: Long): GameDetailsViewModel {
        val viewModel = GameDetailsViewModel(gameId, get(), get())
        viewModel.state.observeForever(stateObserver)
        viewModel.action.observeForever(actionObserver)
        return viewModel
    }
}