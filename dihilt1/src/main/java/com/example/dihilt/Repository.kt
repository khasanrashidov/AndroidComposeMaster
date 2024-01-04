package com.example.dihilt

import javax.inject.Inject

interface GreetingRepository {
    fun getGreeting(name: String): String
}

class GreetingRepositoryImpl @Inject constructor() : GreetingRepository {
    override fun getGreeting(name: String): String {
        return "Hello, $name"
    }
}
