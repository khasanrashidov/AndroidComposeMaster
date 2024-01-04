package com.example.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.ui.theme.ComposeMasterTheme

/**
 * # Table of Contents
  ## 01: Setting Up and Exploring Android Studio
 - Installing Android Studio and SDKs
 - Exploring the Android Studio Interface
 - Creating a Simple Hello World App in Kotlin
 - Introduction to the Gradle Build System
 - Understanding Android Project Structure
  ## 02: Kotlin Basics and First Steps with Jetpack Compose
 - Kotlin Syntax and Basic Programming Concepts
 - Setting up a Jetpack Compose Project
 - Building a Simple UI with Text and Buttons in Compose
  ## 03: Compose Layouts and State Management
 - Understanding Compose Layouts (Row, Column, Box, etc.)
 - Implementing State in Compose (remember, mutableStateOf)
 - Handling User Input and Events in Compose
  ## 04: Styling and Theming in Compose
 - Customizing Colors, Fonts, and Shapes in Compose
 - Implementing Material Design Components and Theming
 - Responsive and Adaptive UI Design in Compose
  ## 05: OpenCV Basics and Image Processing in Android
 - Integrating the OpenCV SDK in an Android Project
 - Basic Image Processing (Loading, Displaying, and Modifying Images)
 - Developing a Simple Photo Editing App with Filters
  ## 06: Navigation and Advanced UI in Compose
 - Implementing Navigation in Compose
 - Advanced Compose UI - Animations, Transitions
 - Building a Multi-Screen App (e.g., a Product Catalog App)
  ## 07: Kotlin Coroutines and Asynchronous Programming
 - Basics of Coroutines and Coroutine Scopes
 - Implementing Network Calls with Retrofit and Coroutines
 - Error Handling and Loading States in Asynchronous Programming
  ## 08: Advanced State Handling and Flow
 - Implementing StateFlow and SharedFlow
 - Building a Real-time Chat Interface
 - Advanced State Management in a Shopping Cart App
  ## 09: Dependency Injection and Architecture
 - Introduction to Dependency Injection with Hilt
 - Architectural Patterns - MVVM and MVI
 - Case Study: Refactoring an App to MVVM/MVI with Hilt
  ## 10: Networking and Remote Data Fetching
 - Advanced Retrofit Usage for API Calls
 - Pagination and Data Fetching Strategies
 - Handling Network Errors and Caching Strategies
  ## 11: Data Persistence - Room Database
 - Setting Up Room Database
 - CRUD Operations in Room
 - Observing Data Changes with LiveData/Flow
  ## 12: Dependency Injection with Hilt
 - Setting Up Hilt in an Android Project
 - Injecting Dependencies in a Compose Application
 - Refactoring an Existing App to Use Hilt in an MVVM/MVI Context
  ## 13: Basic Machine Learning with TensorFlow Lite
 - Introduction to TensorFlow Lite in Android
 - Integrating a Pre-trained ML Model
 - Training a Simple ML Model
 - Integrating and Using a Custom TensorFlow Lite Model
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Welcome to Mobile Programming!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        Greeting("Android")
    }
}