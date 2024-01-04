package com.example.intentimplicit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IntentLauncher()
        }
    }
}

@Preview
@Composable
fun IntentLauncher() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val inputValue = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            label = { Text("Input value") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Divider(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )

        val phoneNumberRegex = Regex("^\\d{10}$")
        val urlRegex = Regex("^(https?|ftp)://[a-z0-9]+(.[a-z0-9]+)+.*$")

        Button(
            onClick = {
                val input = inputValue.value.text
                when {
                    phoneNumberRegex.matches(input) -> {
                        // Launch the phone dialer with the given phone number
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:$input")
                        }
                        context.startActivity(intent)
                    }
                    urlRegex.matches(input) -> {
                        // Launch the browser with the given URL
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(input)
                        }
                        context.startActivity(intent)
                    }
                    else -> {
                        // Do nothing or show an error message
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = inputValue.value.text.isNotBlank()
        ) {
            Text(text = "Launch Intent")

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null
            )
        }
    }
}
