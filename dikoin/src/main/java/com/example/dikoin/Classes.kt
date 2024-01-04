package com.example.dikoin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

// Initialize Koin in your Application class:
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}

// Message.kt
data class Message(val text: String)

// MessageRepository.kt
interface MessageRepository {
    fun getMessage(): Message
}

class MessageRepositoryImpl : MessageRepository {
    override fun getMessage(): Message {
        return Message("Hello from Koin!")
    }
}

// Create a Koin module to provide the repository implementation:
val appModule = module {
    single<MessageRepository> { MessageRepositoryImpl() }
}
