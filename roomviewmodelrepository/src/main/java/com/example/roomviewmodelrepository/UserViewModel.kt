package com.example.roomviewmodelrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val users: Flow<List<User>> = userRepository.users

    fun insertUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            userRepository.insertUser(firstName, lastName)
        }
    }

    fun deleteUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            userRepository.deleteUser(firstName, lastName)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            userRepository.deleteAllUsers()
        }
    }
}


class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

