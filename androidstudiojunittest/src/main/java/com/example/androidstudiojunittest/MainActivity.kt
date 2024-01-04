package com.example.androidstudiojunittest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidstudiojunittest.ui.theme.ComposeMasterTheme

/**
 1. build.gradle
dependencies {
testImplementation 'junit:junit:4.13.2'
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
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("Hello, Android!") }

    Column {
        Text(text, modifier = Modifier.testTag("textView"))
        Button(onClick = { text = "Button clicked!" }, modifier = Modifier.testTag("changeTextButton")) {
            Text("Change Text")
        }
    }
}

// Example function to test
fun isEven(number: Int): Boolean {
    return number % 2 == 0
}
