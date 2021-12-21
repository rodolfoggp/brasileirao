package com.brasileirao.presentation.list

import androidx.lifecycle.Observer
import com.brasileirao.R
import com.brasileirao.data.api.GamesService
import com.brasileirao.data.api.TestGamesService
import com.brasileirao.robot.games
import com.brasileirao.rule.KoinRule
import com.core.resource.ResourceProvider
import com.example.testing_library.koin.asClass
import com.example.testing_library.rule.SyncTestRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class GamesListViewModelTest: KoinTest {

    @get:Rule
    val rule = KoinRule()

    @get:Rule
    val localTestRule = SyncTestRule()

    private val viewModel: GamesListViewModel by inject()
    private val stateObserver = mockk<Observer<GamesListState>>(relaxed = true)
    private val actionObserver = mockk<Observer<GamesListAction>>(relaxed = true)

    private val service by inject<GamesService>().asClass<TestGamesService>()
    private val resourceProvider: ResourceProvider by inject()

    @Before
    fun setup() {
        viewModel.state.observeForever(stateObserver)
        viewModel.action.observeForever(actionObserver)
    }

    @Test
    fun `getGames Should get new games from server`() {
        // Given
        val expectedGames = games { expectedGames }
        val expectedState = GamesListState(games = expectedGames)

        // When
        viewModel.getGames()

        // Then
        verify { stateObserver.onChanged(expectedState) }
    }

    @Test
    fun `getGames Should StopRefreshing after success`() {
        // When
        viewModel.getGames()

        // Then
        verify { actionObserver.onChanged(GamesListAction.StopRefreshing) }
    }

    @Test
    fun `getGames Should StopRefreshing after error`() {
        // Given
        service.returnsError()

        // When
        viewModel.getGames()

        // Then
        verify { actionObserver.onChanged(GamesListAction.StopRefreshing) }
    }

    @Test
    fun `getGames Should DisplayErrorDialog if it fails`() {
        // Given
        service.returnsError()
        val errorMessage = "Houve um erro ao buscar a lista de jogos"
        every { resourceProvider.getString(R.string.error_getting_games_message) } returns errorMessage

        // When
        viewModel.getGames()

        // Then
        verify { actionObserver.onChanged(GamesListAction.DisplayErrorDialog(errorMessage)) }
    }
}