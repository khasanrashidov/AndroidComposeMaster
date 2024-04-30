package com.example.dicarmethodinjection

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
import com.example.dicarmethodinjection.ui.theme.ComposeMasterTheme

/**
 * # Benefits of Method Injection
 * * Flexibility: Method injection allows for greater flexibility as different methods within the same object can use different implementations of the same dependency.
 * * Specificity: It is suitable when the dependency is not needed throughout the object's lifetime but only for specific operations.
 * * Statelessness: Since the dependency is not stored within the object, it helps in keeping the object stateless.
 * # When to Use Method Injection
 * Method injection is particularly useful when:
 * * The dependency is only required for occasional use within the object, not consistently across multiple methods or the object's lifetime.
 * * You need to pass different or new dependency instances each time the method is invoked.
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
                    CarDemo()
                }
            }
        }
    }
}

@Composable
fun CarDemo() {
    // Create an instance of Car
    val car = Car()

    // Create an instance of Engine
    val engine: Engine = GasEngine()  // or ElectricEngine()

    // Pass the engine directly to the startCar method
    val message = car.startCar(engine)

    // Display the car's action
    Text(text = message)
}
