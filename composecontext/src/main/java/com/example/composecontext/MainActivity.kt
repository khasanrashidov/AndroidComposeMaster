package com.example.composecontext

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecontext.ui.theme.ComposeMasterTheme

/**
 * # Compose Context
 * Context in Android Jetpack Compose plays a crucial role in managing and accessing various system resources and services.
 * This includes everything from resources like strings and colors to services such as LayoutInflater and ConnectivityManager.
 *
 * ## Importance of Context
 * - **Access to Resources and Services**: Context provides access to Android's system resources and services,
 *   which are essential for many operations within an app.
 *
 * - **Interacting with Other Components**: It is used to start activities, send broadcasts, and perform other
 *   interactions with different parts of an Android application.
 *
 * - **Accessing Application-Level Data**: Context allows components to access shared preferences and application-wide
 *   singletons, facilitating state management and data sharing across the app.
 *
 * - **Scoping for Compose**: While CompositionLocal can often replace explicit context passing in Compose,
 *   direct access to context is sometimes necessary for operations like displaying toasts or dialogs.
 *
 * ## Best Practices
 * - **Avoid Memory Leaks**: Care should be taken to avoid storing a reference to context in a way that could lead to memory leaks.
 *   In Compose, this can be mitigated by using lifecycle-aware state holders like `remember`.
 *
 * - **Use the Right Context**: It's important to use the appropriate type of context (application vs activity) to avoid issues
 *   like incorrect theming or errors in dialog presentation.
 *
 * - **Scope with CompositionLocal**: Utilize CompositionLocal to provide scoped access to data throughout the composition
 *   hierarchy without the need to pass context explicitly.
 *
 * ## Note
 * Jetpack Compose simplifies context management compared to traditional Android development, but understanding its correct use
 * is still vital for building robust applications.
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMasterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Column(modifier = Modifier.padding(16.dp)) {
        val context = LocalContext.current


        // Button to display a toast
        Button(onClick = {
            Toast.makeText(context, "Hello from Toast!", Toast.LENGTH_SHORT).show()
        }) {
            Text("Show Toast")
        }

        // Button to start a new activity
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Start New Activity")
        }

        // Button to access resources
        Button(onClick = {
            val appName = context.getString(R.string.app_name)
            Toast.makeText(context, "App Name: $appName", Toast.LENGTH_LONG).show()
        }) {
            Text("Get Resource")
        }
    }
}

