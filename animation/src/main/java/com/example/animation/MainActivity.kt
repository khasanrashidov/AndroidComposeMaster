//Prof. H. Jeong
//20230326

package com.example.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.ui.theme.ComposeMasterTheme
import kotlin.random.Random

/**
 * # Jetpack Compose Animations
 * ![Jetpack Compose Animations](https://developer.android.com/jetpack/compose/animation/introduction)
 * ## State-Based Animations:
 * * *animateContentSize()*: Automatically animate the size change of a composable when its content changes.
 * * *animateAsState()*: Animate changes to a value, e.g., animateFloatAsState, animateColorAsState.
 * ## Transition Animations:
 * * *updateTransition()*: Create a transition definition for more complex animations involving multiple states and properties.
 * * *rememberInfiniteTransition()*: Create an infinite transition for repeating animations.
 * ## Visibility and Layout Animations:
 * * *Crossfade()*: For crossfade animations between composables.
 * * *AnimatedVisibility*: Animate the appearance and disappearance of a composable.
 * ## Content Transformations:
 * * *AnimatedContent()*: Animate changes in content with customizable transitions.
 * ## Gesture-Based Animations:
 * * Drag, swipe, and other gesture animations using *Modifier*-based functions like swipeable, draggable.
 * ## Vector Graphics and Drawable Animations:
 * * *AnimatedVectorDrawable*: Animate vector drawables.
 * * *Lottie* (third-party): Integrate Lottie animations (requires Lottie Compose library).
 * ## Canvas and Custom Drawing Animations:
 * * Use *Canvas* with *draw* functions and animate properties like path, shape, color etc.
 * ## Motion Layout Animations (Experimental):
 * * *MotionLayout* (from ConstraintLayout Compose library): Create complex motion and layout animations (experimental and requires additional library).
 * ## Physics-Based Animations:
 * * *Spring*: Spring-based animations for natural motion.
 * * *Decay*: Decay animations that mimic natural slowing down.
 * ## Choreographing Multiple Animations:
 * * Coordinating multiple animations together, for instance, animating several properties of a composable in parallel or in sequence.
 * ## Summary
 *
 * | API Name                      | Description                                            | Example Use Case                              |
 * |-------------------------------|--------------------------------------------------------|-----------------------------------------------|
 * | `animateContentSize`          | Animates the size change of a component.               | Smooth resizing of a text box.                |
 * | `animateColor`                | Animates color properties.                             | Transition between color themes.              |
 * | `animateDp`                   | Animates dimension properties measured in `dp`.        | Moving a component on the screen.             |
 * | `animateFloat`                | Animates floating-point properties.                    | Creating a custom progress indicator.         |
 * | `Crossfade`                   | Creates a crossfade animation between two composables. | Transitioning between different screens/views.|
 * | `AnimatedVisibility`          | Animates the visibility change of a component.         | Showing or hiding UI elements with effects.   |
 * | `rememberInfiniteTransition`  | Creates an infinite animation.                         | Creating a loading spinner or pulsating effect.|
 * | `updateTransition`            | Manages complex state-based animations.                | Coordinating multiple animations in a UI state change. |
 *
 */

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

@Composable
fun AnimatedCircle() {
    var circleSize by remember { mutableFloatStateOf(50f) }
    val sizeAnimation = rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 50f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
    LaunchedEffect(sizeAnimation.value) {
        circleSize = sizeAnimation.value
    }

    var circleColor by remember { mutableStateOf(Color.Red) }
    val colorAnimation = rememberInfiniteTransition(label = "")
        .animateColor(
            initialValue = Color.Red,
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

    LaunchedEffect(colorAnimation.value) {
        colorAnimation.value.also { circleColor = it }
    }

    val random = remember { Random(System.currentTimeMillis()) }
    val randomX by animateFloatAsState(
        targetValue = random.nextFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )
    val randomY by animateFloatAsState(
        targetValue = random.nextFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Canvas(
        modifier = Modifier
            .size(circleSize.dp)
            .padding(16.dp)
    ) {
        val randomXPos = randomX * (size.width - 2 * circleSize) + circleSize
        val randomYPos = randomY * (size.height - 2 * circleSize) + circleSize

        drawCircle(
            color = circleColor,
            center = Offset(randomXPos, randomYPos),
            radius = circleSize / 2
        )
    }
}

@Preview
@Composable
fun DefaultPreview(){
    ComposeMasterTheme {
        AnimatedCircle()
    }
}
