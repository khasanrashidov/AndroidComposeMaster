package com.example.dihilt

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val greetingRepository: GreetingRepository
) : ViewModel() {
    private val _message = mutableStateOf(greetingRepository.getMessage().text)
    val message = _message

    fun updateGreeting(name: String) {
        _message.value = greetingRepository.getMessage(name).text
    }
}
