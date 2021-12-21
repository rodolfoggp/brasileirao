package com.brasileirao.presentation.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.brasileirao.R
import com.brasileirao.databinding.FragmentGameDetailBinding
import com.brasileirao.presentation.details.GameDetailsAction.DisplayErrorDialog
import com.bumptech.glide.Glide
import com.core.view.dateString
import com.core.view.onAction
import com.core.view.onStateChange
import com.core.view.setBackButtonVisible
import com.core.view.timeString
import com.wada811.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val GAME_ID_KEY = "game_id"

class GameDetailsFragment : Fragment(R.layout.fragment_game_detail) {

    private val gameId: Long by lazy { requireArguments().getLong(GAME_ID_KEY) }
    private val viewModel: GameDetailsViewModel by viewModel { parametersOf(gameId) }
    private val binding: FragmentGameDetailBinding by viewBinding()
    private val glide by lazy { Glide.with(this) }
    private val highlightsAdapter = HighlightsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, ::handleAction)
        viewModel.getGame()
    }

    private fun setupLayout() {
        setBackButtonVisible(true)
        with(binding.highlightsRecyclerView) {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            val dividerItemDecoration =
                DividerItemDecoration(context, linearLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            adapter = highlightsAdapter
        }
    }

    private fun handleState(state: GameDetailsState) {
        with(binding) {
            state.game?.let { game ->
                with(game) {
                    detailTeam1Name.text = team1Performance.team
                    detailTeam1Score.text = team1Performance.score.toString()
                    glide.load(team1Performance.badge).into(detailTeam1Badge)

                    detailTeam2Name.text = team2Performance.team
                    detailTeam2Score.text = team2Performance.score.toString()
                    glide.load(team2Performance.badge).into(detailTeam2Badge)

                    timeAndLocation.text = String.format(
                        getString(R.string.time_and_location_details),
                        dateTime.dateString(),
                        dateTime.timeString(),
                        location,
                    )

                    highlightsAdapter.updateData(highlights)
                }
            }
        }
    }

    private fun handleAction(action: GameDetailsAction) {
        when (action) {
            is DisplayErrorDialog -> displayErrorDialog(action.message)
        }
    }

    private fun displayErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_title)
            .setMessage(message)
            .setNeutralButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance(gameId: Long) =
            GameDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(GAME_ID_KEY, gameId)
                }
            }
    }
}