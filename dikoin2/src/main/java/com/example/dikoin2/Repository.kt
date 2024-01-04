package com.example.dikoin2

interface GreetingRepository {
    fun getGreeting(name: String): String
}

class GreetingRepositoryImpl : GreetingRepository {
    override fun getGreeting(name: String): String {
        return "Hello, $name"
    }
}
