package com.example.dependencyinjection

// GreetingService
interface GreetingService {
    fun greet(name: String): String
}

class GreetingServiceImpl : GreetingService {
    override fun greet(name: String): String {
        return "Hello, $name!"
    }
}

// FarewellService
interface FarewellService {
    fun farewell(name: String): String
}

class FarewellServiceImpl : FarewellService {
    override fun farewell(name: String): String {
        return "Goodbye, $name!"
    }
}