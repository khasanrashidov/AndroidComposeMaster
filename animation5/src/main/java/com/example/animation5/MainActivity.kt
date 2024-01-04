//Prof. H. Jeong
//20230329
// Download the snippets and store in SnippetBasics.kt and SnippetAdvanced
// SnippetBasic.kt
//https://github.com/android/snippets/blob/13a17cb1d733c6fb80db742c3ab6c072793afbb5/compose/snippets/src/main/java/com/example/compose/snippets/animations/AnimationSnippets.kt#L131-L134
// SnippetAdvanced.kt
//https://github.com/android/snippets/blob/13a17cb1d733c6fb80db742c3ab6c072793afbb5/compose/snippets/src/main/java/com/example/compose/snippets/animations/AdvancedAnimationSnippets.kt
// add button to activate

package com.example.animation5

import android.gesture.Gesture
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.animation5.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.launch

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


data class App(val name: String, val content: @Composable () -> Unit)

val apps = listOf(
    App("AnimatedVisibilitySample") { AnimatedVisibilitySample() },
    App("AnimatedVisibilityWithEnterAndExit") { AnimatedVisibilityWithEnterAndExit() },
    App("AnimatedVisibilityMutable") { AnimatedVisibilityMutable() },
    App("AnimatedVisibilityAnimateEnterExitChildren") { AnimatedVisibilityAnimateEnterExitChildren() },
    App("AnimatedVisibilityTransition") { AnimatedVisibilityTransition() },
    App("AnimateAsStateSimple") { AnimateAsStateSimple() },
    App("AnimatedContentSimple") { AnimatedContentSimple() },
    App("AnimatedContentTransitionSpec") { AnimatedContentTransitionSpec(1) },
    App("AnimatedContentSizeTransform") { AnimatedContentSizeTransform() },
    App("AnimateContentSizeSimple") { AnimateContentSizeSimple() },
    App("CrossfadeSimple") { CrossfadeSimple() },
    App("UpdateTransitionAnimatedVisibility") { UpdateTransitionAnimatedVisibility() },
    App("RememberInfiniteTransitionSimple") { RememberInfiniteTransitionSimple() },
    App("AnimatableSimple"){AnimatableSimple(true)},
//    App("TargetBasedAnimationSimple"){TargetBasedAnimationSimple() },
    App("AnimationSpecTween"){AnimationSpecTween()},
    App("AnimationSpecSpring"){AnimationSpecSpring()},
    App("AnimationSpecTween"){AnimationSpecTween()},
    App("AnimationSpecKeyframe"){AnimationSpecKeyframe()},
    App("AnimationSpecRepeatable"){AnimationSpecRepeatable()},
    App("AnimationSpecInfiniteRepeatable"){AnimationSpecInfiniteRepeatable()},
    App("AnimationSpecInfiniteRepeatable"){AnimationSpecInfiniteRepeatable()},
    App("AnimationSpecSnap"){AnimationSpecSnap()},
    App("AnimatedVectorDrawable"){ AnimatedVectorDrawable() },
//Advanced
    App("Gesture"){ Gesture() },
//App("swipeToDismiss"){Modifier.swipeToDismiss()}
)


@Composable
fun AppActivator() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var appNumber by remember { mutableStateOf(TextFieldValue("")) }
    var currentAppContent by remember { mutableStateOf<(@Composable () -> Unit)?>(null) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("App Launcher", modifier = Modifier.weight(1f))

                TextField(
                    value = appNumber,
                    onValueChange = { newValue -> appNumber = newValue },
                    label = { Text("App Number") },
                    modifier = Modifier.weight(2f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    // Get the app content based on the entered app number
                    val appIndex = appNumber.text.toIntOrNull()?.minus(1)
                    if (appIndex != null && appIndex in apps.indices) {
                        val appName = apps[appIndex].name
                        currentAppContent = apps[appIndex].content

                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Activated: $appName")
                        }
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Invalid app number")
                        }
                    }
                }) {
                    Text(text = "Activate App")
                }
            }
        },
        content = { paddingValue ->
            Modifier.padding(paddingValue)
            Box(modifier = Modifier.fillMaxSize()) {
                currentAppContent?.invoke()
            }
        }
    )
}

@Composable
fun MyApp() {
    MaterialTheme {
        Surface(color = Color.LightGray) {
            AppActivator()
        }
    }
}


