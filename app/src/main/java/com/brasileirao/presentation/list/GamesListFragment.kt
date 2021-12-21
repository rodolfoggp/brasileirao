package com.brasileirao.presentation.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brasileirao.R
import com.brasileirao.databinding.FragmentGamesListBinding
import com.brasileirao.presentation.details.GameDetailsFragment
import com.brasileirao.presentation.list.GamesListAction.DisplayErrorDialog
import com.brasileirao.presentation.list.GamesListAction.StopRefreshing
import com.core.view.onAction
import com.core.view.onStateChange
import com.core.view.setBackButtonVisible
import com.wada811.viewbinding.viewBinding
import org.koin.android.ext.android.inject

class GamesListFragment : Fragment(R.layout.fragment_games_list) {

    private val binding: FragmentGamesListBinding by viewBinding()
    private val viewModel: GamesListViewModel by inject()
    private val gamesAdapter = GamesListAdapter(::onGameClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, ::handleAction)
    }

    private fun setupLayout() {
        setBackButtonVisible(false)
        with(binding) {
            with(gamesRecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = gamesAdapter
            }
            swipeRefresh.setOnRefreshListener { viewModel.getGames() }
        }
    }

    private fun handleState(state: GamesListState) {
        gamesAdapter.updateData(state.games)
    }

    private fun handleAction(action: GamesListAction) {
        when (action) {
            is StopRefreshing -> binding.swipeRefresh.isRefreshing = false
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

    override fun onResume() {
        super.onResume()
        viewModel.getGames()
    }

    private fun onGameClicked(gameId: Long) {
        val fragment = GameDetailsFragment.newInstance(gameId)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
