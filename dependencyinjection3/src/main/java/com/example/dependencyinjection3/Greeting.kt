package com.example.dependencyinjection3

interface GreetingService {
    fun greet(name: String): String
}

class GreetingServiceImpl : GreetingService {
    override fun greet(name: String): String {
        return "Hello, $name!"
    }
}
