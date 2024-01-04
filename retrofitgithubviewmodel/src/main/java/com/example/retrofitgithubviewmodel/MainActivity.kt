package com.example.retrofitgithubviewmodel

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitgithubviewmodel.ui.theme.ROOMTheme

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


//@Composable
//fun FetchReposScreen() {
//    // TODO: ViewModel Factory
//    val viewModel: ReposViewModel = viewModel()
//    val repos by viewModel.repos.collectAsState(initial = emptyList())
//
//    ReposList(repos)
//}

@Composable
fun FetchReposScreen() {
    // Providing the ReposViewModelFactory
    val gitHubApi = RetrofitInstance.api
    val viewModelFactory = ReposViewModelFactory(gitHubApi)

    // Using the provided factory to get an instance of ReposViewModel
    val viewModel: ReposViewModel = viewModel(factory = viewModelFactory)
    val repos by viewModel.repos.collectAsState(initial = emptyList())

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
