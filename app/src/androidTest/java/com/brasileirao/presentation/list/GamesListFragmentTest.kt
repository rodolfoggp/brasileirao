package com.brasileirao.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.brasileirao.R
import com.brasileirao.domain.model.Game
import com.brasileirao.robot.games
import com.brasileirao.rule.FragmentTestRule
import com.core.view.weekDayAndDateString
import com.core.view.timeString
import com.example.testing_library.instrumented.checkItContains
import com.example.testing_library.instrumented.checkViewIsDisplayed
import com.example.testing_library.instrumented.dialogIsDisplayedWithText
import com.example.testing_library.instrumented.onView
import com.example.testing_library.instrumented.setValueOnMainThread
import com.example.testing_library.instrumented.swipeDownOn
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertFalse
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.test.KoinTest

class GamesListFragmentTest : KoinTest {

    private val fakeState = MutableLiveData<GamesListState>()
    private val fakeAction = MutableLiveData<GamesListAction>()
    private val viewModel = mockk<GamesListViewModel>(relaxed = true) {
        every { state } returns fakeState
        every { action } returns fakeAction
    }

    @get:Rule
    val rule = FragmentTestRule(GamesListFragment::class.java) {
        viewModel { viewModel }
    }

    @Test
    fun recyclerView_ShouldBeVisible_WhenFragmentIsOpened() {
        checkViewIsDisplayed(R.id.gamesRecyclerView)
    }

    @Test
    fun fragment_ShouldGetGames_WhenFragmentStarts() {
        verify { viewModel.getGames() }
    }

    @Test
    fun swipeRefresh_ShouldGetGames_ASecondTime() {
        // When
        swipeDownOn(R.id.swipeRefresh)

        // Then
        verify(exactly = 2) { viewModel.getGames() }
    }

    @Test
    fun recyclerView_ShouldShowItemsProvidedByViewModel() {
        // Given
        val games = games { expectedGames }

        // When
        fakeState.setValueOnMainThread(GamesListState(games = games))

        // Then
        onView(R.id.gamesRecyclerView).checkItContains(games, ::gameMatcher)
    }

    @Test
    fun stopRefreshingAction_ShouldSetRefreshingToFalse() {
        // Given
        swipeDownOn(R.id.swipeRefresh)

        // When
        fakeAction.setValueOnMainThread(GamesListAction.StopRefreshing)

        // Then
        rule.scenario.onFragment {
            val swipeRefresh = it.requireActivity().findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
            assertFalse(swipeRefresh.isRefreshing)
        }
    }

    @Test
    fun displayErrorDialog_ShouldDisplayDialog_WithErrorMessage() {
        // Given
        val message = "Error message"

        // When
        fakeAction.setValueOnMainThread(GamesListAction.DisplayErrorDialog(message))

        // Then
        dialogIsDisplayedWithText(message)
    }

    private fun gameMatcher(game: Game) =
        with(game) {
            allOf(
                hasDescendant(withText(team1Performance.team)),
                hasDescendant(withText(team2Performance.team)),
                hasDescendant(withText(team1Performance.score.toString())),
                hasDescendant(withText(team2Performance.score.toString())),
                hasDescendant(withText(dateTime.weekDayAndDateString())),
                hasDescendant(withText(dateTime.timeString())),
            )
        }
}
