package com.core.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.core.viewmodel.StateViewModel
import com.core.viewmodel.ViewModel

fun <State, Action> Fragment.onStateChange(
    viewModel: ViewModel<State, Action>,
    handleState: (State) -> Unit
) {
    viewModel.state.observe(this) { state -> handleState(state) }
}

fun <State, Action> Fragment.onAction(
    viewModel: ViewModel<State, Action>,
    handleAction: (Action) -> Unit
) {
    viewModel.action.observe(this) { action -> handleAction(action) }
}

fun <State> Fragment.onStateChange(
    viewModel: StateViewModel<State>,
    handleState: (State) -> Unit
) {
    viewModel.state.observe(this) { state -> handleState(state) }
}

fun Fragment.setBackButtonVisible(value: Boolean) =
    (requireActivity() as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(value)