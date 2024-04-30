package com.example.dependencyinjection3

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

//Inject the GreetingService in your MainActivity and pass it to your main Composable:
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var greetingService: GreetingService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(greetingService)
        }
    }
}

// Create a Hilt module to provide the GreetingService:
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGreetingService(): GreetingService = GreetingServiceImpl()
}

// Annotate the Application class with @HiltAndroidApp:
@HiltAndroidApp
class MyApp : Application()

//Update your main Composable to accept the GreetingService as a parameter:
@Composable
fun MyApp(greetingService: GreetingService) {

            Column {
                Text(text = "Hello, World!")
                GreetUser(greetingService, "John")
                GreetUser(greetingService, "Jane")
            }

}

//Finally, update the GreetUser Composable to accept the GreetingService as a parameter:
@Composable
fun GreetUser(greetingService: GreetingService, name: String) {
    val greeting = remember(name) { greetingService.greet(name) }
    Text(text = greeting)
}

