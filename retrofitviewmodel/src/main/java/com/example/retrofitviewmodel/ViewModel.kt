//package com.example.retrofitviewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//
//// LiveData/MutableLiveData + observeAsState vs. Flow/MutableStateFlow + collectAsState
//class UsersViewModel : ViewModel() {
//    private val _users = MutableStateFlow<List<User>>(emptyList())
//    val users: Flow<List<User>> = _users
//
//    private val api = RetrofitInstance.api
//
//    init {
//        fetchUsers()
//    }
//
//    private fun fetchUsers() {
//        viewModelScope.launch {
//            val response = api.getUsers()
//            if (response.isSuccessful) {
//                response.body()?.data?.let { fetchedUsers ->
//                    _users.value = fetchedUsers
//                }
//            }
//        }
//    }
//}
//
//// TODO: UswersViewModel Factory
//// UsersViewModel Factory
//class UsersViewModelFactory(private val reqResApi: ReqResApi) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
//            return UsersViewModel(reqResApi) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}


package com.example.retrofitviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// LiveData/MutableLiveData + observeAsState vs. Flow/MutableStateFlow + collectAsState
class UsersViewModel(private val api: ReqResApi) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: Flow<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val response = api.getUsers()
            if (response.isSuccessful) {
                response.body()?.data?.let { fetchedUsers ->
                    _users.value = fetchedUsers
                }
            }
        }
    }
}

// UsersViewModel Factory
class UsersViewModelFactory(private val reqResApi: ReqResApi) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(reqResApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
