package com.example.dependencyinjectionabcd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.dependencyinjectionabcd.ui.theme.ComposeMasterTheme

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
                    MyApp()
                }
            }
        }
    }
}

// Classes
class A {
    fun getMessage(): String = "Hello from A"
}

class B(val a: A) {
    fun getMessage(): String = "Hello from B and ${a.getMessage()}"
}

class C(val b: B) {
    fun getMessage(): String = "Hello from C and ${b.getMessage()}"
}

class D(val c: C) {
    fun getMessage(): String = "Hello from D and ${c.getMessage()}"
}

@Composable
fun MyApp() {
    val a = remember { A() }
    val b = remember { B(a) }
    val c = remember { C(b) }
    val d = remember { D(c) }

    // Use the instance of D in your app
    val message = d.getMessage()
    Text(text = message)
}
