package com.example.testing2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location.asStateFlow()

    suspend fun fetchLocation() {
        val newLocation = locationRepository.getLocation()
        _location.value = newLocation
    }
}
