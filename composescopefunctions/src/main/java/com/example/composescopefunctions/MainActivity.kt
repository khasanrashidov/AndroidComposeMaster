package com.example.composescopefunctions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composescopefunctions.ui.theme.ComposeMasterTheme


/**
 * # Compose Scope Functions
 * Executes the block of code using the receiver as its argument and returns the result.
 * This function is helpful in Compose for isolating and managing side effects or transformations
 * that depend on external data.
 *
 * ## Usage
 * `let` is useful for conditional execution of code blocks where you need to perform null checks
 * or temporary transformations.
 *
 * @param T the receiver type.
 * @param block the block to execute, which transforms the receiver into a different type.
 * @return Returns the result of the block, typically a transformation of the receiver.
 */

/**
 * Comparison of Kotlin Scope Functions.
 *
 * | Function | Object Reference | Return Value         | Typical Use                                 |
 * |----------|------------------|----------------------|---------------------------------------------|
 * | let      | it               | Result of lambda     | Transforming or operating on a nullable object and using the result. |
 * | apply    | this             | Context object       | Configuring properties of an object.        |
 * | run      | this             | Result of lambda     | Configuring and computing a result.         |
 * | with     | this             | Result of lambda     | Like run, but as a non-extension function.  |
 * | also     | it               | Context object       | Additional actions (e.g., logging) without altering the object. |
 *
 * ## Details
 *
 * ### let
 * Use `let` for null checks and when you need to transform an object into something else.
 * It's particularly useful for chaining nullable operations or transformations.
 *
 * ### apply
 * `apply` is good for initializing or configuring objects. As it returns the context object,
 * it allows for chaining further operations on the object being configured.
 *
 * ### run
 * Similar to `apply`, but `run` returns the result of the lambda. It's useful for when you
 * need to both configure an object and return a result.
 *
 * ### with
 * Non-extension function similar to `run`, useful for executing a block of code that returns
 * a result with a non-nullable receiver. It's great for working with initialized objects.
 *
 * ### also
 * Use `also` when you want to perform additional operations (like logging) while keeping the
 * context object unchanged. It allows you to inspect the object without affecting the result.
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMasterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserCard()
                }
            }
        }
    }
}

data class User(var name: String, var age: Int)

@Composable
fun UserCard() {
    // Create a state for user
    var user by remember { mutableStateOf(User("Alice", 30)) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Display user information
        Text("Name: ${user.name}")
        Text("Age: ${user.age}")

        Button(onClick = {
            // Use 'apply' to update user state within the block
            // apply: This function is used to modify the existing user object.
            // The modifications are made directly on the object's properties, and
            // the object itself is returned and reassigned to the state.
            user = user.apply {
                name = "Bob"
                age = 25
            }
        }) {
            Text("Update User with Apply")
        }

        Button(onClick = {
            // Use 'also' to log details and then update user state
            // also: It logs an action to the console before making a modification,
            // illustrating the typical use case of also for logging or additional side effects.
            user = user.also {
                println("Updating user from ${it.name} to Charlie")
                it.name = "Charlie"
            }
        }) {
            Text("Log and Update Name with Also")
        }

        Button(onClick = {
            // Use 'let' to perform an operation and update the UI conditionally
            // let: Useful for conditionally executing code blocks, here it increments
            // the user's age only if it's greater than 20.
            user.let {
                if (it.age > 20) {
                    it.age += 1
                    user = it // Update the user state with new age
                }
            }
        }) {
            Text("Increment Age with Let")
        }

        Button(onClick = {
            // Use 'run' to calculate a new value and update state
            // run: Transforms the user data and returns a new User object,
            // which replaces the old one. This function is beneficial
            // when you need to compute a value and immediately return it,
            // which might involve altering the object deeply or replacing it entirely.
            user = user.run {
                copy(name = "Daisy", age = age + 5)
            }
        }) {
            Text("Modify User with Run")
        }
    }
}