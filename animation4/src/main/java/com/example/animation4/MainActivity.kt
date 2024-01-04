package com.example.animation4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animation4.ui.theme.ComposeMasterTheme

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
    var targetColor by remember { mutableStateOf(Color.Blue) }
    var targetSize by remember { mutableStateOf(100.dp) }
    var targetFloat by remember { mutableStateOf(0f) }
    var targetDp by remember { mutableStateOf(0.dp) }
    var targetTransition by remember { mutableStateOf(false) }
    var targetInfiniteTransition by remember { mutableStateOf(false) }
    var targetContentSize by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            targetColor = if (targetColor == Color.Blue) Color.Green else Color.Blue
        }) {
            Text("Animate Color")
        }

        Button(onClick = { targetSize = if (targetSize == 100.dp) 200.dp else 100.dp }) {
            Text("Animate Content Size")
        }

        Button(onClick = { targetFloat = if (targetFloat == 0f) 360f else 0f }) {
            Text("Animate Float")
        }

        Button(onClick = { targetDp = if (targetDp == 0.dp) 20.dp else 0.dp }) {
            Text("Animate Dp")
        }

        Button(onClick = { targetTransition = !targetTransition }) {
            Text("Animate Transition")
        }
        Button(onClick = { targetInfiniteTransition = !targetInfiniteTransition }) {
            Text("Animate Infinite Transition")
        }

        Button(onClick = { targetContentSize = !targetContentSize }) {
            Text("Animate Content Size")
        }

        val animatedColor by animateColorAsState(targetColor)
        val animatedSize by animateDpAsState(targetSize)
        val animatedFloat by animateFloatAsState(targetFloat)
        val animatedDp by animateDpAsState(targetDp)

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

        // The remaining animations from the second code snippet
        val transitionState = if (targetTransition) AnimationState.A else AnimationState.B
        val transition = updateTransition(transitionState)
        val infiniteTransition = rememberInfiniteTransition()
        val animatedProgress by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )


        val scaleX by transition.animateFloat(
            transitionSpec = {
                if (targetTransition) {
                    tween(durationMillis = 1000)
                } else {
                    tween(durationMillis = 1000)
                }
            }
        ) { state ->
            if (state == AnimationState.A) 1f else 2f
        }


        val scaleY by transition.animateFloat(
            transitionSpec = {
                if (targetTransition) {
                    tween(durationMillis = 1000)
                } else {
                    tween(durationMillis = 1000)
                }
            }
        ) { state ->
            if (state == AnimationState.A) 1f else 0.5f
        }

        val color by transition.animateColor(
            transitionSpec = {
                if (targetTransition) {
                    tween(durationMillis = 1000)
                } else {
                    tween(durationMillis = 1000)
                }
            }
        ) { state ->
            when (state) {
                AnimationState.A -> Color.Blue
                AnimationState.B -> Color.Red
            }
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(color)
                .scale(scaleX, scaleY)
                .padding(32.dp)
        ) {
            val progress = (animatedProgress * 100).toInt()
            Text(text = "Progress: $progress%", fontSize = 20.sp)
        }

        // animateContentSize
        val contentSize = if (targetContentSize) 100.dp else 200.dp
        Box(
            modifier = Modifier
                .animateContentSize()
                .size(contentSize)
                .background(Color.Gray)
                .padding(32.dp)
        ) {
            Text(text = if (targetContentSize) "Small" else "Large", fontSize = 14.sp)
        }
    }
}

private enum class AnimationState { A, B }

