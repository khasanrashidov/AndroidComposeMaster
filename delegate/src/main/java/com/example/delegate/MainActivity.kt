package com.example.delegate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.delegate.ui.theme.ComposeMasterTheme
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


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
                    UpperCasedDelegateExample()
                }
            }
        }
    }
}

// Custom delegate
class UpperCasedState(private val originalState: State<String>) : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return originalState.value.uppercase()
    }
}

// Extension function to create uppercased state
fun State<String>.upperCased(): UpperCasedState {
    return UpperCasedState(this)
}

@Composable
fun UpperCasedDelegateExample() {
    var originalText by remember { mutableStateOf("Hello, World!") }
    val upperCasedText by originalText.upperCased()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Original text: $originalText")
        Text("Uppercased text: $upperCasedText")

        Spacer(Modifier.height(16.dp))

        Button(onClick = { originalText = "New Text" }) {
            Text("Change Text")
        }
    }
}

@Composable
fun String.upperCased() = remember(this) {
    mutableStateOf(this.toUpperCase())
}
@Preview(showBackground = true)
@Composable
fun UpperCasedDelegateExamplePreview() {
    UpperCasedDelegateExample()
}
