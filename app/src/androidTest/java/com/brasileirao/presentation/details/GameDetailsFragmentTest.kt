package com.brasileirao.presentation.details

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.brasileirao.R
import com.brasileirao.domain.model.Highlight
import com.brasileirao.presentation.details.GameDetailsAction.DisplayErrorDialog
import com.brasileirao.robot.games
import com.brasileirao.rule.FragmentTestRule
import com.example.testing_library.instrumented.checkItContains
import com.example.testing_library.instrumented.checkViewContainsText
import com.example.testing_library.instrumented.dialogIsDisplayedWithText
import com.example.testing_library.instrumented.onView
import com.example.testing_library.instrumented.setValueOnMainThread
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.test.KoinTest


class GameDetailsFragmentTest : KoinTest {

    private val fakeState = MutableLiveData<GameDetailsState>()
    private val fakeAction = MutableLiveData<GameDetailsAction>()
    private val viewModel = mockk<GameDetailsViewModel>(relaxed = true) {
        every { state } returns fakeState
        every { action } returns fakeAction
    }
    private val bundle: Bundle = Bundle().apply {
        putLong("game_id", 1)
    }

    @get:Rule
    val rule = FragmentTestRule(GameDetailsFragment::class.java, bundle) {
        viewModel { viewModel }
    }

    @Test
    fun fragment_ShouldDisplayCorrespondingGameData() {
        // Given
        val game = games { expectedGames[1] }
        val expectedState = GameDetailsState(game)

        // When
        fakeState.setValueOnMainThread(expectedState)

        // Then
        checkViewContainsText(R.id.detailTeam1Score, "1")
        checkViewContainsText(R.id.detailTeam2Score, "0")
        checkViewContainsText(R.id.detailTeam1Name, "Corinthians")
        checkViewContainsText(R.id.detailTeam2Name, "Vasco")

        onView(R.id.highlightsRecyclerView).checkItContains(game.highlights, ::highlightMatcher)
    }

    @Test
    fun displayErrorDialogAction_ShouldDisplayErrorDialog_WithGivenMessage() {
        // Given
        val message = "Error message"

        // When
        fakeAction.setValueOnMainThread(DisplayErrorDialog(message))

        // Then
        dialogIsDisplayedWithText(message)
    }

    private fun highlightMatcher(highlight: Highlight) =
        with(highlight) {
            allOf(
                hasDescendant(withText(description)),
                hasDescendant(withText("$minute\'")),
            )
        }
}