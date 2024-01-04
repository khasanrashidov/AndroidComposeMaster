package com.example.dependencyinjectionabcd2

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
import com.example.dependencyinjectionabcd2.ui.theme.ComposeMasterTheme

class MainActivity2 : ComponentActivity() {
    private val injector = Injector()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(injector)
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

// Injector class to manage dependencies
class Injector {
    val a: A = A()
    val b: B by lazy { B(a) }
    val c: C by lazy { C(b) }
    val d: D by lazy { D(c) }
}

@Composable
fun MyApp(injector: Injector) {
    val d = remember { injector.d }

    // Use the instance of D in your app
    val message = d.getMessage()
    Text(text = message)
}
