package com.example.roomviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    val users: Flow<List<User>> = userDao.getAll()
/**
 * | Mechanism | ViewModel | UI | Description |
 * |-----------|-----------|----|-------------|
 * | `Flow` | `viewModelScope.launch { flow.collect() }` | `Flow.collectAsState()` | A `Flow` is a type in Kotlin that can asynchronously emit multiple values. It's part of Kotlin's coroutines API and is used for handling streams of data that produce values over time. |
 * | `LiveData` | `LiveData<T>.observe(lifecycleOwner, Observer { })` | `LiveData.observeAsState()` | `LiveData` is a lifecycle-aware data holder with the observer pattern. It respects the lifecycle of Android app components, such as activities, fragments, or services, and only updates app component observers that are in an active lifecycle state. |
 * | `RxJava` | `Observable.subscribe()` | `Observable.subscribeAsState()` | `RxJava` is a Java VM implementation of Reactive Extensions, which is a library for composing asynchronous and event-based programs by using observable sequences. It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together declaratively. |
 * | `Coroutines` | `viewModelScope.launch { }` | N/A | Coroutines are a Kotlin feature that convert async callbacks for long-running tasks, such as database or network access, into sequential code. |
 * | `Callbacks` | `callbackFunction()` | N/A | A callback is a piece of executable code that is passed as an argument to other code, which is expected to call back (execute) the argument at some convenient time. The invocation may be immediate as in a synchronous callback or it might happen at a later time, as in an asynchronous callback. |
 */

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
