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
            onValueChange = { newText -> text = newText },
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
