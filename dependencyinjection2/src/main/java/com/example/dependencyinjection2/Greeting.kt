package com.example.dependencyinjection2

// First, let's define the dependencies:

interface GreetingService {
    fun greet(name: String): String
}

class GreetingServiceImpl : GreetingService {
    override fun greet(name: String): String {
        return "Hello, $name!"
    }
}
