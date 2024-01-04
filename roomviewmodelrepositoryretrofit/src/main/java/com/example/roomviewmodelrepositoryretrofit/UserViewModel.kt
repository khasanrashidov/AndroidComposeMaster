package com.example.roomviewmodelrepositoryretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val roomUsers: Flow<List<User>> = userRepository.roomUsers
    val retrofitUsers: Flow<List<User>> = userRepository.retrofitUsers
    val githubUsers: StateFlow<GithubUser?> = userRepository.githubUsers

    fun fetchRetrofitUsers() {
        viewModelScope.launch {
            userRepository.fetchRetrofitUsers()
        }
    }

    fun fetchGithubUser(username: String) {
        viewModelScope.launch {
            try {
                userRepository.fetchGithubUser(username).first()
            } catch (e: Exception) {
                // Handle the error or update the state as needed
            }
        }
    }

    // ROOM database
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

//    suspend fun getGithubUser(username: String): GithubUser? {
//        return userRepository.getGithubUser(username).firstOrNull()
//    }
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
