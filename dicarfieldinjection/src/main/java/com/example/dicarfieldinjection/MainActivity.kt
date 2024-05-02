package com.example.dicarfieldinjection

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
import com.example.dicarfieldinjection.ui.theme.ComposeMasterTheme

/**
 * # Benefits of Field Injection
 * * Simplicity: Field injection is straightforward and easy to implement.
 * * Readability: It makes the code more readable by clearly indicating the dependencies of the object.
 * * Ease of Use: Field injection is convenient for objects that require the same dependency throughout their lifetime.
 * # When to Use Field Injection
 * Field injection is suitable when:
 * * The dependency is required throughout the object's lifetime and is not expected to change.
 * * The object is a singleton or has a long lifecycle where the dependency is needed consistently.
 * * The object is a framework-managed component like an Android Activity or Fragment.

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
    // Create an instance of Car without engine
    val car = Car()

    // Create an instance of Engine
    val engine: Engine = GasEngine()  // or ElectricEngine()

    // Inject the engine into the Car using the setter
    car.installEngine(engine)

    // Display the car's action
    Text(text = car.startCar())
}
