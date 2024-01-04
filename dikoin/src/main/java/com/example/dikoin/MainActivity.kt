package com.example.dikoin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dikoin.ui.theme.ComposeMasterTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    private val messageRepository: MessageRepository by get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayMessage(messageRepository.getMessage())
                }
            }
        }
    }
}

@Composable
fun DisplayMessage(message: Message) {
    Text(text = message.text)
}
