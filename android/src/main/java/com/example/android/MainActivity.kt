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
 * ## 1. Introduction to Android Development and Jetpack Compose
 * - Overview of Android Development
 * - Introduction to Jetpack Compose
 * - Setting Up the Development Environment
 * Creating a Simple Hello World App in Compose
 * ## 2. Kotlin for Jetpack Compose
 * - Kotlin Basics: Syntax and Key Concepts
 * - Understanding Kotlin's Role in Compose
 * - Kotlin Functions and Control Structures
 * ## 3. Compose Fundamentals, Styling, and Theming
 * - Understanding Composables and Composition
 * - Basic UI Elements in Compose (Text, Image, Button)
 * - Composable Layouts: Row, Column, Box, etc.
 * - Theming and Styling in Compose
 * - Fonts, Colors, and Shapes Customization
 * - Implementing Dark and Light Themes
 * ## 4. Animation and Motion in Compose
 * - Basics of Animation in Compose
 * - State-based Animations and Transitions
 * - Complex Motion Layouts and Animated Graphics
 * ## 5. Navigation and User Interactions in Compose
 * - Implementing Navigation in Compose
 * - Handling User Inputs and Gestures
 * - Building Multi-Screen Applications
 * ## 6. OpenCV in Android
 * - Integrating OpenCV with Android
 * - Basic Image Processing Techniques
 * - Building a Simple Image Editing Application
 * ## 7. State Management in Compose
 * - Managing State and Data in Compose
 * - Implementing State Management with State and MutableState
 * - State Hoisting and Unidirectional Data Flow
 * ## 8. Architectural Patterns in Android Development
 * - Overview of MVC, MVP, MVVM, and MVI
 * - Understanding and Implementing Each Pattern
 * - Comparisons and Use Cases in Android Development
 * ## 9. Networking, Repository, and Remote Data Fetching
 * - Implementing Network Calls with Retrofit
 * - Setting Up a Repository for Data Handling
 * - Handling Network Errors and Caching Strategies
 * - LiveData, StateFlow, and SharedFlow in Compose
 * ## 10. Advanced State Management and Asynchronous Programming
 * - Advanced Concepts in State Management
 * - Kotlin Coroutines for Asynchronous Tasks
 * - Building Reactive UIs with Compose and State Management Tools
 * ## 11. Data Persistence and Dependency Injection
 * - Room Database Integration
 * - Integrating Room with LiveData and StateFlow
 * - Dependency Injection with Hilt
 * ## 12. Introduction to Machine Learning in Android
 * - Basics of Machine Learning on Android
 * - Integrating TensorFlow Lite in Android
 * - Implementing a Simple ML Model in an App
 * ## 13. Advanced Machine Learning in Android
 * - Advanced Concepts in Machine Learning
 * - Deep Dive into TensorFlow Lite
 * - Building Complex ML Models
 * - Integrating Advanced ML Models in Android Applications
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