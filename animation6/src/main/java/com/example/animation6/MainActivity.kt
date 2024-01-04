//20230329
//Prof. H. Jeong
// Included all the 9 animation methods

package com.example.animation6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animation6.ui.theme.ComposeMasterTheme

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
                    AnimationExamples()
                }
            }
        }
    }


    @Composable
    fun AnimationExamples() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                listOf(
                    Any(),
                    Any(),
                    Any(),
                    Any(),
                    Any(),
                    Any(),
                    Any(),
                    Any(),
                    Any()
                )
            ) { index, _ ->
                when (index) {
                    0 -> AnimatedVisibilityExample()
                    1 -> RememberInfiniteTransitionExample()
                    2 -> CrossfadeExample()
                    3 -> AnimatedContentSizeExample()
                    4 -> UpdateTransitionExample()
                    5 -> AnimateAsStateExample()
                    6 -> AnimationExample()
                    7 -> AnimatableExample()
                    8 -> AnimationStateExample()
                }
            }
        }
    }


    @Composable
    fun AnimatedVisibilityExample() {
        val visible = remember { mutableStateOf(true) }

        Button(onClick = { visible.value = !visible.value }) {
            Text("Toggle AnimatedVisibility")
        }
        AnimatedVisibility(visible = visible.value) {
            Text("AnimatedVisibility example")
        }
    }

    @Composable
    fun RememberInfiniteTransitionExample() {
        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )
        Button(onClick = { /* No action needed, animation runs indefinitely */ }) {
            Text("Infinite Transition")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )
    }

    @Composable
    fun CrossfadeExample() {
        val showTextA = remember { mutableStateOf(true) }
        Button(onClick = { showTextA.value = !showTextA.value }) {
            Text("Toggle Crossfade")
        }

        Crossfade(targetState = showTextA.value) { show ->
            if (show) {
                Text("Text A")
            } else {
                Text("Text B")
            }
        }
    }

    @Composable
    fun AnimatedContentSizeExample() {
        val expanded = remember { mutableStateOf(false) }
        Button(onClick = { expanded.value = !expanded.value }) {
            Text("Toggle Animated Content Size")
        }

        Box(
            modifier = Modifier
                .background(Color.Green)
                .animateContentSize()
        ) {
            if (expanded.value) {
                Text("Expanded content", modifier = Modifier.padding(16.dp))
            } else {
                Text("Collapsed content", modifier = Modifier.padding(8.dp))
            }
        }
    }

    @Composable
    fun UpdateTransitionExample() {
        val expanded = remember { mutableStateOf(false) }
        val transition = updateTransition(targetState = expanded.value)
        val width by transition.animateDp(
            transitionSpec = { spring(stiffness = Spring.StiffnessLow) }
        ) { state -> if (state) 200.dp else 100.dp }

        Button(onClick = { expanded.value = !expanded.value }) {
            Text("Toggle UpdateTransition")
        }

        Box(
            modifier = Modifier
                .width(width)
                .height(100.dp)
                .background(Color.Cyan)
        )
    }

    @Composable
    fun AnimateAsStateExample() {
        val expanded = remember { mutableStateOf(false) }
        val color by animateColorAsState(if (expanded.value) Color.Yellow else Color.Magenta)
        Button(onClick = { expanded.value = !expanded.value }) {
            Text("Toggle Color Animation")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )
    }

    @Composable
    fun AnimationExample() {
        val expanded = remember { mutableStateOf(false) }
        val width by animateDpAsState(targetValue = if (expanded.value) 200.dp else 100.dp)

        Button(onClick = { expanded.value = !expanded.value }) {
            Text("Toggle Animation")
        }

        Box(
            modifier = Modifier
                .width(width)
                .height(100.dp)
                .background(Color.LightGray)
        )
    }

    @Composable
    fun AnimatableExample() {
        val animatable = remember { Animatable(initialValue = 100.dp, Dp.VectorConverter) }
        val expanded = remember { mutableStateOf(false) }

        Button(onClick = { expanded.value = !expanded.value }) {
            Text("Toggle Animatable")
        }

        LaunchedEffect(expanded.value) {
            animatable.animateTo(
                targetValue = if (expanded.value) 200.dp else 100.dp,
                animationSpec = tween(durationMillis = 500)
            )
        }

        Box(
            modifier = Modifier
                .width(animatable.value)
                .height(100.dp)
                .background(Color.Yellow)
        )
    }


    @Composable
    fun AnimationStateExample() {
        val animationState = remember {
            AnimationState(
                initialValue = 100.dp,
                typeConverter = Dp.VectorConverter
            )
        }
        val expanded = remember { mutableStateOf(false) }

        Button(onClick = {
            expanded.value = !expanded.value
        }) {
            Text("Toggle AnimationState")
        }

        LaunchedEffect(expanded.value) {
            animationState.animateTo(
                targetValue = if (expanded.value) 200.dp else 100.dp,
                animationSpec = tween(durationMillis = 1000)
            )
        }

        Box(
            modifier = Modifier
                .width(animationState.value)
                .height(100.dp)
                .background(Color.Gray)
        )
    }
}
