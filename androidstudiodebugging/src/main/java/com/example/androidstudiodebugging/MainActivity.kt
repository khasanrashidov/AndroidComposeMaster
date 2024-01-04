package com.example.androidstudiodebugging

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebuggingExampleScreen()
        }
    }
}

/**
 # Android Studio Debugging
## Setting Breakpoints:
Teach how to set breakpoints in DebuggingExampleScreen, for example on the line counter++.

## Inspecting Variables:
Demonstrate how to inspect the values of counter and textToShow when the breakpoint is hit.

## Stepping Through Code:
Show how to step through the code using "Step Over" and "Step Into" in the debugger.

## Evaluating Expressions:
Use the "Evaluate Expression" feature to test different values and expressions at runtime.

## Identifying and Fixing Bugs:
Use the debugger to identify the intentional bug when textToShow becomes null.
Discuss how to fix the bug and demonstrate the corrected behavior.

## Logcat Usage:
- Explain how to open the Logcat window in Android Studio (View > Tool Windows > Logcat).
Demonstrate how log messages appear in Logcat when the button is clicked.
Filtering Log Messages:
- Show how to filter log messages by log level (e.g., Debug) or by a tag (e.g., "DebuggingExample").
Reading and Interpreting Logs:
- Teach how to read Logcat logs and use them to understand app behavior, especially when diagnosing issues.
Adding Log Statements:
- Explain the different log levels (Verbose, Debug, Info, Warn, Error, and Assert) and when to use them.
Show how to add Log.d (debug) statements to Kotlin code to log important information.
Using Logcat for Debugging:
- Demonstrate how Logcat can be used in conjunction with breakpoints and the debugger for a comprehensive debugging approach.
 */

@Composable
fun DebuggingExampleScreen() {
    var counter by remember { mutableStateOf(0) }
    var textToShow by remember { mutableStateOf("Start counting...") }

    Column {
        Button(onClick = {
            counter++
            Log.d("DebuggingExample", "Counter value: $counter") // Log statement

            textToShow = if (counter % 2 == 0) {
                "Even number"
            } else {
                // Intentional bug for demonstration
                null
            } ?: "Oops! Encountered a null."

            Log.d("DebuggingExample", "Displayed text: $textToShow") // Log statement
        }) {
            Text("Increase Counter")
        }
        Text(textToShow)
    }
}
