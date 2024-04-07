package com.example.modelmvvm

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.modelmvvm.ui.theme.ComposeMasterTheme


/**
 * # Models
 * [Android Developer](https://developer.android.com/topic/libraries/architecture/viewmodel)
 *
 * ## Control Flows
 *
 *
 * | Pattern | Input Handling          | Control Flow                | Processing                 | Output                               |
 * |---------|-------------------------|-----------------------------|----------------------------|--------------------------------------|
 * | MVC     | View captures user input| Input sent to Controller    | Controller modifies Model  | Controller updates View based on Model |
 * | MVP     | View receives user input| View delegates to Presenter | Presenter updates Model    | Presenter instructs View to update   |
 * | MVVM    | View binds to ViewModel commands| ViewModel processes input | ViewModel modifies Model based on input | View automatically updates via data binding |
 * | MVI     | View emits Intents      | Intents processed to update State | State updated based on Intents | View updates to reflect new State    |
 *
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
                    CounterScreen(CounterViewModel())
                }
            }
        }
    }
}


