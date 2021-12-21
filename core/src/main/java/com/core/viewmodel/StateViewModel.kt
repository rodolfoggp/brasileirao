package com.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class StateViewModel<State>(initialState: State): DisposableViewModel() {
    private val _state = MutableLiveData(initialState)

    val state: LiveData<State> = _state

    fun setState(state: (State) -> State) {
        _state.value = state(_state.value!!)
    }
}
