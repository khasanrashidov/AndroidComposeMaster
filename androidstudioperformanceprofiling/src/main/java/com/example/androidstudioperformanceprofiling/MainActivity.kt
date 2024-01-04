package com.example.androidstudioperformanceprofiling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.androidstudioperformanceprofiling.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.math.BigInteger

/**
 * # Performance Profiling
## Run the App:
- Start your app on a device or emulator.

## Open Android Profiler:
- In Android Studio, open the Android Profiler by clicking View > Tool Windows > Profiler.

## Start Profiling:
- Select your device and app from the Android Profiler.

## Monitor CPU, Memory, and Network:
- Click on each of the operations in your app (CPU, Memory, Network) and observe the changes in the Profiler.
- For CPU, you can observe the CPU usage spike during the CPU-intensive operation.
- For Memory, look at the memory allocation when the memory-intensive operation is triggered.
- For Network, monitor the network request being made and the data being received.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PerformanceScreen()
                }
            }
        }
    }
}

@Composable
fun PerformanceScreen() {
    val coroutineScope = rememberCoroutineScope()
    var cpuOperationResult by remember { mutableStateOf("Not started") }
    var memoryOperationResult by remember { mutableStateOf("Not started") }
    var networkOperationResult by remember { mutableStateOf("Not started") }

    Column {
        Button(onClick = { cpuOperationResult = performCpuIntensiveOperation() }) {
            Text("CPU Intensive Operation")
        }
        Text(cpuOperationResult)

        Button(onClick = { memoryOperationResult = performMemoryIntensiveOperation() }) {
            Text("Memory Intensive Operation")
        }
        Text(memoryOperationResult)

        Button(onClick = {
            coroutineScope.launch {
                networkOperationResult = performNetworkRequest()
            }
        }) {
            Text("Network Request")
        }
        Text(networkOperationResult)
    }
}

fun performCpuIntensiveOperation(): String {
    var factorial = BigInteger.ONE
    for (i in 1..10000) {  // Reduced the range for practicality
        factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
    }
    return "CPU Operation Completed"
}

fun performMemoryIntensiveOperation(): String {
    val largeList = MutableList(100000) { it }
    return "Memory Operation Completed with list size: ${largeList.size}"
}

suspend fun performNetworkRequest(): String {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)
    return try {
        val posts = service.getPosts() // Perform network request
        "Network Request Completed with ${posts.size} posts"
    } catch (e: Exception) {
        "Network Request Failed: ${e.message}"
    }
}


data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post> // Define Post data class as per your API response
}