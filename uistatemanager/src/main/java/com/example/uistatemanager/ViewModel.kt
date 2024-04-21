package com.example.uistatemanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UIStateManagerViewModel : ViewModel() {
    // LiveData
    private val _liveData = MutableLiveData("LiveData Initial")
    val liveData: LiveData<String> = _liveData

    // StateFlow
    private val _stateFlow = MutableStateFlow("StateFlow Initial")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    // SharedFlow
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow: SharedFlow<String> = _sharedFlow.asSharedFlow()

    // Flow
    private val _flow = MutableStateFlow("Flow Initial")
    val flow: Flow<String> = _flow

    fun updateLiveData() {
        _liveData.value = "LiveData Updated: ${System.currentTimeMillis()}"
    }

    fun updateStateFlow() {
        _stateFlow.value = "StateFlow Updated: ${System.currentTimeMillis()}"
    }

    fun emitToSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow Message: ${System.currentTimeMillis()}")
        }
    }

    fun updateFlow() {
        viewModelScope.launch {
            _flow.value = "Flow Updated: ${System.currentTimeMillis()}"
        }
    }
}
