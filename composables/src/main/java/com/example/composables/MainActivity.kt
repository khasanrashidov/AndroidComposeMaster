package com.example.composables

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Surface(color = Color.White) {
        Column(modifier = Modifier.padding(16.dp)) {
            Greeting(name = "Alice")
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
            Counter()
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
            TextWithInput()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting(name = "Alice")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    Counter()
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text(text = "I've been clicked $count times")
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithInputPreview() {
    TextWithInput()
}

@Composable
fun TextWithInput() {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    Column {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(text = "Enter some text") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You entered: ${textState.text}")
    }
}
