package com.example.dihilt



import javax.inject.Inject

data class Message(val text: String)

interface GreetingRepository {
    fun getMessage(name: String = ""): Message
}

class GreetingRepositoryImpl @Inject constructor() : GreetingRepository {
    override fun getMessage(name: String): Message {
        return Message("Hello from Hilt${if (name.isNotEmpty()) ", $name" else ""}!")
    }
}