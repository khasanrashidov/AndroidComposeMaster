package com.example.androidmvi

sealed class MainState {
    object Idle : MainState()
    data class Greeting(val message: String) : MainState()
}
