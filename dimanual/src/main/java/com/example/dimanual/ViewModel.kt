package com.example.dimanual

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingViewModel(private val repository: GreetingRepository) : ViewModel() {
    val greeting: MutableLiveData<String> = MutableLiveData()


    fun updateGreeting(name: String) {
        greeting.value = repository.getGreeting(name)
    }
}

