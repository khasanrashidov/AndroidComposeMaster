package com.example.dihilt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(private val repository: GreetingRepository) :
    ViewModel() {
    val greeting: MutableLiveData<String> = MutableLiveData()

    fun updateGreeting(name: String) {
        greeting.value = repository.getGreeting(name)
    }
}

//with @HiltViewModel in Hilt 2.28 and later, the ViewModelFactory is not needed.