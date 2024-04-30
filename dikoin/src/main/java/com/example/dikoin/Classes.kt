package com.example.dikoin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

// 2. Initialize Koin in your Application class:
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger() // Log Koin-related info (optional)
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}

// Message.kt
data class Message(val text: String)

// MessageRepository.kt
interface MessageRepository {
    fun getMessage(name: String = ""): Message
}

class MessageRepositoryImpl : MessageRepository {
    override fun getMessage(name: String): Message {
        return Message("Hello from Koin${if (name.isNotEmpty()) ", $name" else ""}!")
    }
}

// 1. Create a Koin module to provide the repository implementation:
val appModule = module {
    single<MessageRepository> { MessageRepositoryImpl() }
    viewModel { GreetingViewModel(get()) }
}