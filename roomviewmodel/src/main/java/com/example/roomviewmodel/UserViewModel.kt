package com.example.roomviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    val users: Flow<List<User>> = userDao.getAll()

    fun insertUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            val newUser = User(uid = 0, firstName = firstName, lastName = lastName)
            userDao.insertAll(newUser)
        }
    }

    fun deleteUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            val user = userDao.findByName(firstName, lastName)
            user?.let { userDao.delete(it) }
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            userDao.deleteAll()
        }
    }
}


class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
