//package com.example.retrofitgithubviewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class ReposViewModel : ViewModel() {
//    private val _repos = MutableStateFlow<List<GitHubRepo>>(emptyList())
//    val repos: StateFlow<List<GitHubRepo>> = _repos
//
//    private val api = RetrofitInstance.api
//
//    init {
//        fetchRepos()
//    }
//
//    private fun fetchRepos() {
//        viewModelScope.launch {
//            val response = api.getUserRepos("octocat")
//            if (response.isSuccessful) {
//                response.body()?.let { fetchedRepos ->
//                    _repos.value = fetchedRepos
//                }
//            }
//        }
//    }
//}
//


package com.example.retrofitgithubviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReposViewModel(private val api: GitHubApi) : ViewModel() {
    private val _repos = MutableStateFlow<List<GitHubRepo>>(emptyList())
    val repos: StateFlow<List<GitHubRepo>> = _repos

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModelScope.launch {
            val response = api.getUserRepos("gnoejh")
            if (response.isSuccessful) {
                response.body()?.let { fetchedRepos ->
                    _repos.value = fetchedRepos
                }
            }
        }
    }
}

// ReposViewModel Factory
class ReposViewModelFactory(private val gitHubApi: GitHubApi) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            return ReposViewModel(gitHubApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
