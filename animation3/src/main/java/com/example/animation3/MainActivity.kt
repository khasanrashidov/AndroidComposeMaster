package com.example.animation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation3.ui.theme.ComposeMasterTheme


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


@Preview(showBackground = true)
@Composable
fun MyApp() {
    var targetColor by remember { mutableStateOf(Color.Blue) }
    var targetSize by remember { mutableStateOf(100.dp) }
    var targetFloat by remember { mutableStateOf(0f) }
    var targetDp by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            targetColor =
                if (targetColor == Color.Blue) Color.Red else Color.Red
        }) {
            Text("Animate Color")
        }

        Button(onClick = { targetSize = if (targetSize == 100.dp) 200.dp else 100.dp }) {
            Text("Animate Content Size")
        }

        Button(onClick = { targetFloat = if (targetFloat == 0f) 360f else 0f }) {
            Text("Animate Float")
        }
        Button(onClick = { targetDp = if (targetDp == 0.dp) 50.dp else 0.dp }) {
            Text("Animate Dp")
        }

        val animatedColor by animateColorAsState(
            targetColor,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        val animatedSize by animateDpAsState(
            targetSize,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        val animatedFloat by animateFloatAsState(
            targetFloat,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        val animatedDp by animateDpAsState(
            targetDp,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        Box(
            modifier = Modifier
                .size(animatedSize)
                .background(animatedColor)
                .padding(animatedDp)
        ) {
            Text(
                text = "Hello",
                modifier = Modifier.rotate(animatedFloat)
            )
        }
    }
}

