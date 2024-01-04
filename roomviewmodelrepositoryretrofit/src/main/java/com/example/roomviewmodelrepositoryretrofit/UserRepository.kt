package com.example.roomviewmodelrepositoryretrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepository(private val userDao: UserDao) {
    // 1. Room
    val roomUsers: Flow<List<User>> = userDao.getAll()

    // 2. Retrofit (API)
    private val _retrofitUsers = MutableStateFlow<List<User>>(emptyList())
    val retrofitUsers: StateFlow<List<User>> = _retrofitUsers.asStateFlow()

    private val apiService = provideRetrofit()

    suspend fun fetchRetrofitUsers() {
        val response = apiService.getUsers()
        if (response.isSuccessful) {
            response.body()?.let { usersResponse ->
                val users = usersResponse.map {
                    User(
                        uid = it.id,
                        firstName = it.first_name,
                        lastName = it.last_name
                    )
                }
                _retrofitUsers.emit(users)
            }
        } else {
            throw IllegalStateException("Error fetching users from API: ${response.message()}")
        }
    }

    // 3. GitHub
    private val githubApi = provideGithubApi()
    private val _githubUsers = MutableStateFlow<GithubUser?>(null)
    val githubUsers: StateFlow<GithubUser?> get() = _githubUsers


    fun fetchGithubUser(username: String): Flow<GithubUser> = flow {
        val githubUser = githubApi.getUser(username)
        emit(githubUser)
    }.catch { e ->
        // Handle exceptions when fetching data from the API
        // Emit an appropriate error message or handle the error in your desired way
        throw e
    }



    // Methods for ROOM database
    suspend fun insertUser(firstName: String, lastName: String) {
        val newUser = User(uid = 0, firstName = firstName, lastName = lastName)
        userDao.insertAll(newUser)
    }

    suspend fun deleteUser(firstName: String, lastName: String) {
        val user = userDao.findByName(firstName, lastName)
        user.let { userDao.delete(it) }
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAll()
    }
}
