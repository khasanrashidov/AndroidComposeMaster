package com.example.androidstudio

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
import com.example.androidstudio.ui.theme.ComposeMasterTheme

/**
# Android Studio Basics
## Android Studio Overview:
- Familiarizing with the Android Studio interface.
- Understanding the Project Structure view vs. Android view.
- Navigating through different files and directories in the project.

## Creating a New Project:
- Steps to create a new Android project.
- Explanation of the options available during the new project setup.
- Understanding the structure of the newly created project.

## Building and Running the App:
- How to build and run an Android app in an emulator or on a physical device.
- Understanding the build.gradle files and Gradle Sync.
- Configuring different build variants.

## Version Control Integration:
- Using Git with Android Studio.
- Basic Git operations like commit, push, pull, and branch management.

## Debugging:
- Introduction to the Debugger tool.
- Setting breakpoints and inspecting variables.
- Using the Logcat window to view log messages.

## Writing and Running Unit Tests:
- Creating and running unit tests.
- Introduction to testing frameworks like JUnit and Espresso.

## UI Testing with Espresso:
- Writing UI tests to simulate user interactions.
- Running and debugging UI tests.

## Performance Monitoring and Profiling:
- Using the Android Profiler to monitor app performance.
- Understanding CPU, memory, and network usage.

## Refactoring and Code Generation:
- Utilizing Android Studio's refactoring tools.
- Generating code (like getters, setters, and boilerplate code).

## Emulator Management:
- Setting up and managing Android Virtual Devices (AVDs).
- Emulator features and shortcuts.

## Android Studio Plugins and SDK Manager:
- Installing and managing plugins.
- Using the SDK Manager to keep Android SDK tools and platforms updated.

## Coding Tips and Shortcuts:
- Useful keyboard shortcuts.
- Code navigation tips and tricks.

## Publishing the App:
- Preparing an app for release (ProGuard, signing the APK).
- Generating a signed APK or App Bundle for distribution.
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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
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