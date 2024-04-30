package com.example.dihiltmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingViewModel(private val repository: GreetingRepository) : ViewModel() {
    private val _greeting = MutableLiveData<String>()
    val greeting : LiveData<String> = _greeting

    fun updateGreeting(name: String) {
        _greeting.value = repository.getGreeting(name)
    }
}

