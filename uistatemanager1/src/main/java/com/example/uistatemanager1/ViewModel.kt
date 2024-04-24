package com.example.uistatemanager1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class UIStateManagerViewModel : ViewModel() {
    //1. LiveData for UI updates sensitive to lifecycle states
    private val _liveData = MutableLiveData("Initial LiveData Value")
    val liveData: LiveData<String> = _liveData

    //2. StateFlow for continuously updating UI with the latest state
    private val _stateFlow = MutableStateFlow("Initial StateFlow Value")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    //3. SharedFlow for one-time event messaging like snackbars, navigation events
    private val _eventFlow = MutableSharedFlow<String>()
    val eventFlow: SharedFlow<String> = _eventFlow.asSharedFlow()

    //4. Regular Flow for complex data processing that shouldn't be kept in memory
    val numberFlow: Flow<Int> = flow {
        for (i in 1..100000) {
            emit(i)
            delay(1000)
        }
    }

    fun updateLiveData() {
        _liveData.value = "1. LiveData Updated: ${System.currentTimeMillis()}"
    }

    fun updateStateFlow() {
        _stateFlow.value = "2. StateFlow Updated: ${System.currentTimeMillis()}"
    }

    fun emitEvent() {
        viewModelScope.launch {
            _eventFlow.emit("3. Event at ${System.currentTimeMillis()}")
        }
    }
}