package com.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class ViewModel<State, Action>(initialState: State) : DisposableViewModel() {
    private val _state = MutableLiveData(initialState)
    val state: LiveData<State> = _state

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    fun setState(state: (State) -> State) {
        _state.value = state(_state.value!!)
    }

    fun sendAction(action: () -> Action) {
        _action.value = action()
    }
}

