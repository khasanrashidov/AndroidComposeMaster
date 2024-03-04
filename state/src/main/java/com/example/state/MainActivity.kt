package com.example.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.launch

/**
 * Demonstrates the implementation of state management in Jetpack Compose through various examples.
 * State in Compose is a core concept that enables dynamic and responsive UIs. This example covers:
 *
 * - Basic state management with `remember` and `mutableStateOf`.
 * - Preservation of state across configuration changes with `rememberSaveable`.
 * - Managing lists and showing snackbars within a scaffold layout.
 * - Utilization of coroutines for asynchronous UI actions.
 *
 * Principles and Tools:
 * - **Remember and MutableState**: Used for holding observable state within a composable that
 *   recomposes when state changes.
 * - **RememberSaveable**: Extends `remember` to save and restore state across configuration changes
 *   or process death.
 * - **ScaffoldState**: Manages UI components such as snackbars and drawers in a Scaffold layout.
 * - **Coroutines**: Used for executing asynchronous operations, such as showing a snackbar.
 *
 * The examples illustrate the unidirectional data flow in Compose, where UI elements automatically
 * update in response to state changes, promoting a clear separation of concerns and simplifying state management.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   MyApp()
                }
            }
        }
    }
}




@Composable
fun MyApp() {
    MaterialTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                CounterExample()
                Spacer(Modifier.height(16.dp))
                TextInputExample()
                Spacer(Modifier.height(16.dp))
                ItemListExample()
            }
        }
    }
}

@Composable
fun CounterExample() {
    var counter by remember { mutableStateOf(0) }

    Column {
        Text("Counter Example", fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))

        Text("Counter value: $counter")
        Spacer(Modifier.height(8.dp))

        Button(onClick = { counter++ }) {
            Text("Increment Counter")
        }
    }
}

@Composable
fun TextInputExample() {
    var text by rememberSaveable { mutableStateOf("") }

    Column {
        Text("Text Input Example", fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))

        TextField(
            value = text,
            onValueChange = { newText -> text = newText},
            label = { Text("Enter some text") }
        )
    }
}

@Composable
fun ItemListExample() {
    var items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3")) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column {
        Text("Item List Example", fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            val newItem = "Item ${items.size + 1}"
            items = items + newItem
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Added: $newItem")
            }
        }) {
            Text("Add Item")
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(items) { item ->
                Text(item, Modifier.padding(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}
