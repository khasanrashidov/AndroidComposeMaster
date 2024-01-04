// Prof. Hong Jeong
// Cyclic Dependency
// https://developer.android.com/jetpack/compose/dependency-injection
// https://developer.android.com/jetpack/compose/dependency-injection#cyclic-dependency
// https://developer.android.com/jetpack/compose/dependency-injection#interfaces
// https://developer.android.com/jetpack/compose/dependency-injection#injector
// https://developer.android.com/jetpack/compose/dependency-injection#injector-1
// https://developer.android.com/jetpack/compose/dependency-injection#injector-2
// A~E
// a -> _a -> e -> _e -> "E"
//
package com.example.dependencyinjectionabcd3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.dependencyinjectionabcd3.ui.theme.ComposeMasterTheme

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
                    MyApp(Injector())
                }
            }
        }
    }
}

// a cyclic dependency between classes A and E, where A depends on E and vice versa:
//class A(val e: E) {
//    fun getMessage(): String = "Hello from A and ${e.getMessage()}"
//}
//
//class E(val a: A) {
//    fun getMessage(): String = "Hello from E and ${a.getMessage()}"
//}

//Define interfaces for the classes involved in the cyclic dependency:
interface IA {
    fun getMessage(): String
}

interface IE {
    fun getMessage(): String
}

//Update the classes to implement the interfaces:
class A(val e: IE) : IA {
    override fun getMessage(): String = "Hello from A and ${e.getMessage()}"
}

class E(val a: IA) : IE {
    override fun getMessage(): String = "Hello from E and ${a.getMessage()}"
}

//Modify the injector to provide the instances with the help of provider functions:
class Injector {
    private lateinit var _a: A
    private lateinit var _e: E

    val a: IA
        get() = _a

    val e: IE
        get() = _e

    init {
        provideInstances()
    }

    private fun provideInstances() {
        _a = A(object : IE {
            override fun getMessage(): String = "E" // e.getMessage()
        })

        _e = E(object : IA {
            override fun getMessage(): String = "A" //a.getMessage()
        })
    }
}

// we initialize the instances of A and E with anonymous implementations of the required interfaces.
// Once both instances are created, their dependencies will point to the correct instances,
// effectively breaking the cyclic dependency.

@Composable
fun MyApp(injector: Injector) {
    val a = remember { injector.a }
    val e = remember { injector.e }

    // Use the instances of A and E in your app
    val messageA = a.getMessage()
    val messageE = e.getMessage()
    Column {
        Text(text = messageA)
        Text(text = messageE)
    }
}
