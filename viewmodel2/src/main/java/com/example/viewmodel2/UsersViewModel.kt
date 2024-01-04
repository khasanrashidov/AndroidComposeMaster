package com.example.viewmodel2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsersViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users

    fun addUser(name: String) {
        val newUser = User(id = _users.value!!.size + 1, name = name)
        _users.value = _users.value!! + newUser
    }
}
