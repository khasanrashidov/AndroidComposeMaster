package com.example.animationgesture


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val circleColor = remember { mutableStateOf(Color.Red) }
    val circleRadius = remember { mutableStateOf(50.dp) }
    val scaleFactor = remember { mutableStateOf(1f) }
    val animatedScaleFactor = animateFloatAsState(targetValue = scaleFactor.value).value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        scaleFactor.value = 1.5f
                        tryAwaitRelease()
                        scaleFactor.value = 1f
                        // Change the circle's color to a random color on tap
                        circleColor.value = Color(
                            red = Random.nextFloat(),
                            green = Random.nextFloat(),
                            blue = Random.nextFloat(),
                            alpha = 1f
                        )
                    }
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                    change.consume()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .offset(offsetX.value.dp, offsetY.value.dp)
                .background(Color.Transparent)
        ) {
            drawCircle(
                color = circleColor.value,
                radius = circleRadius.value.toPx() * animatedScaleFactor,
                style = Stroke(width = 4.dp.toPx())
            )
        }
    }
}
