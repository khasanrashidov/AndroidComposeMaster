package com.example.androidmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state

    fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.LoadGreeting -> loadGreeting()
        }
    }

    private fun loadGreeting() {
        viewModelScope.launch {
            _state.value = MainState.Greeting("Hello, welcome to Mobile Programming!")
        }
    }
}
