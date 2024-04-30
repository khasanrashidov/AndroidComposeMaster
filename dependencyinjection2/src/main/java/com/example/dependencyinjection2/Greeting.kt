package com.example.dependencyinjection2



//interface GreetingService {
//    fun greet(name: String): String
//}
//
//class GreetingServiceImpl : GreetingService {
//    override fun greet(name: String): String {
//        return "Hello, $name!"
//    }
//}

// GreetingService
interface GreetingService {
    fun greet(name: String): String
//    fun farewell(name: String): String
}

class GreetingServiceImpl : GreetingService {
    override fun greet(name: String): String {
        return "Hello, $name!"
    }

//    override fun farewell(name: String): String {
//        return "Goodbye!"
//    }
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