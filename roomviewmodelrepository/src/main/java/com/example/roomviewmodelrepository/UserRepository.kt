package com.example.roomviewmodelrepository

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val users: Flow<List<User>> = userDao.getAll()

    suspend fun insertUser(firstName: String, lastName: String) {
        val newUser = User(uid = 0, firstName = firstName, lastName = lastName)
        userDao.insertAll(newUser)
    }

    suspend fun deleteUser(firstName: String, lastName: String) {
        val user = userDao.findByName(firstName, lastName)
        user?.let { userDao.delete(it) }
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAll()
    }
}
