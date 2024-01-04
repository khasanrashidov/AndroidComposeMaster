package com.example.androidmvi

sealed class MainIntent {
    object LoadGreeting : MainIntent()
}
