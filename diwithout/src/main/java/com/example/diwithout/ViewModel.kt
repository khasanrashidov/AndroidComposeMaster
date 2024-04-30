package com.example.diwithout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingViewModel : ViewModel() {
    // create an instance of GreetingRepository instead of passing it as a parameter
    private val repository: GreetingRepository = GreetingRepository()

    private val _greeting: MutableLiveData<String> = MutableLiveData()
    val greeting: LiveData<String> = _greeting

    fun updateGreeting(name: String) {
        _greeting.value = repository.getGreeting(name)
    }
}