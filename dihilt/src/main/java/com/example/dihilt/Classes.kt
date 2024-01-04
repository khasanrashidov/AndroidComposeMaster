package com.example.dihilt

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application()

// Message.kt
data class Message(val text: String)

// MessageRepository.kt
interface MessageRepository {
    fun getMessage(): Message
}

// MessageRepositoryImpl.kt
class MessageRepositoryImpl @Inject constructor() : MessageRepository {
    override fun getMessage(): Message {
        return Message("Hello from Hilt!")
    }
}

// Create a Hilt module to provide the repository implementation:
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindMessageRepository(impl: MessageRepositoryImpl): MessageRepository
}
