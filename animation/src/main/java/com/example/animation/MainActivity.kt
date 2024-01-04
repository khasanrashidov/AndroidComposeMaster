//Prof. H. Jeong
//20230326

package com.example.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.ui.theme.ComposeMasterTheme
import kotlin.random.Random

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
                    AnimatedCircle()
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AnimatedCircle() {
//    var circleSize by remember { mutableStateOf(50f) }
//    var circleColor by remember { mutableStateOf(Color.Red) }
//    val random = remember { Random(System.currentTimeMillis()) }
//    val coroutineScope = rememberCoroutineScope()
//    val randomX by animateFloatAsState(
//        targetValue = random.nextFloat(),
//        animationSpec = infiniteRepeatable(
//            tween(1000, easing = LinearEasing)
//        )
//    )
//    val randomY by animateFloatAsState(
//        targetValue = random.nextFloat(),
//        animationSpec = infiniteRepeatable(
//            tween(1000, easing = LinearEasing)
//        )
//    )
//
//    val sizeAnimation = rememberInfiniteTransition()
//        .animateFloat(
//            initialValue = 50f,
//            targetValue = 200f,
//            animationSpec = infiniteRepeatable(
//                animation = tween(durationMillis = 1000),
//                repeatMode = RepeatMode.Reverse
//            )
//        )
//
//    LaunchedEffect(sizeAnimation.value) {
//        circleSize = sizeAnimation.value
//    }
//
//    val colorAnimation = rememberInfiniteTransition()
//        .animateColor(
//            initialValue = Color.Red,
//            targetValue = Color.Blue,
//            animationSpec = infiniteRepeatable(
//                animation = tween(durationMillis = 1000),
//                repeatMode = RepeatMode.Reverse
//            )
//        )
//
//    LaunchedEffect(colorAnimation.value) {
//        circleColor = colorAnimation.value
//    }
//
//    Canvas(
//        modifier = Modifier
//            .size(circleSize.dp)
//            .padding(16.dp)
//    ) {
//        val randomX = random.nextFloat() * (size.width - 2 * circleSize) + circleSize
//        val randomY = random.nextFloat() * (size.height - 2 * circleSize) + circleSize
//
//        drawCircle(
//            circleColor,
//            center = Offset(randomX, randomY),
//            radius = circleSize / 2
//        )
//    }
//}
//
//@Composable
//fun AnimatedScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        AnimatedCircle()
//    }
//}
@Composable
fun AnimatedCircle() {
    var circleSize by remember { mutableStateOf(50f) }
    val sizeAnimation = rememberInfiniteTransition()
        .animateFloat(
            initialValue = 50f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    LaunchedEffect(sizeAnimation.value) {
        circleSize = sizeAnimation.value
    }

    var circleColor by remember { mutableStateOf(Color.Red) }
    val colorAnimation = rememberInfiniteTransition()
        .animateColor(
            initialValue = Color.Red,
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

    LaunchedEffect(colorAnimation.value) {
        circleColor = colorAnimation.value
    }

    val random = remember { Random(System.currentTimeMillis()) }
    val randomX by animateFloatAsState(
        targetValue = random.nextFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val randomY by animateFloatAsState(
        targetValue = random.nextFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Canvas(
        modifier = Modifier
            .size(circleSize.dp)
            .padding(16.dp)
    ) {
        val randomXPos               = randomX * (size.width - 2 * circleSize) + circleSize
        val randomYPos = randomY * (size.height - 2 * circleSize) + circleSize

        drawCircle(
            color = circleColor,
            center = Offset(randomXPos, randomYPos),
            radius = circleSize / 2
        )
    }
}
