package com.example.dikoin


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GreetingViewModel(
    private val repository: MessageRepository
) : ViewModel() {
    private val _message = MutableStateFlow("Hello from Koin!")
    val message = _message.asStateFlow()

    fun updateGreeting(name: String) {
        _message.value = repository.getMessage(name).text
    }
}

