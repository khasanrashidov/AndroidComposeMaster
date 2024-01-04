package com.example.retrofitgithub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.retrofitgithub.ui.theme.ROOMTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ROOMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FetchReposScreen()
                }
            }
        }
    }
}


@Composable
fun FetchReposScreen() {
    val api = RetrofitInstance.api
    var repos by remember { mutableStateOf(emptyList<GitHubRepo>()) }

    LaunchedEffect(Unit) {
        launch {
            val response = api.getUserRepos("gnoejh")
            if (response.isSuccessful) {
                response.body()?.let { fetchedRepos ->
                    repos = fetchedRepos
                }
            }
        }
    }

    ReposList(repos)
}


@Composable
fun ReposList(repos: List<GitHubRepo>) {
    LazyColumn {
        items(repos) { repo ->
            Column {
                Text("ID: ${repo.id}")
                Text("Name: ${repo.name}")
                Text("Description: ${repo.description ?: "No description"}")
                Text("URL: ${repo.html_url}")
            }
        }
    }
}
