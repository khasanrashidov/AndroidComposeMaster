package com.example.dikoin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingViewModel(private val repository: GreetingRepository) : ViewModel() {
    val greeting: MutableLiveData<String> = MutableLiveData()

    fun updateGreeting(name: String) {
        greeting.value = repository.getGreeting(name)
    }
}
