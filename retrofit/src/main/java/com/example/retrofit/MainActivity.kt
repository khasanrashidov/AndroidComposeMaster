package com.example.retrofit

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofit.ui.theme.ROOMTheme
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
                    FetchUsersScreen()
                }
            }
        }
    }
}


@Composable
fun FetchUsersScreen() {
    val api = RetrofitInstance.api
    var users by remember { mutableStateOf(emptyList<User>()) }

    LaunchedEffect(Unit) {
        launch {
            val response = api.getUsers()
            if (response.isSuccessful) {
                response.body()?.data?.let { fetchedUsers ->
                    users = fetchedUsers
                }
            }
        }
    }

    UsersList(users)
}

@Composable
fun UsersList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            Column {
                Text("ID: ${user.id}")
                Text("Email: ${user.email}")
                Text("First Name: ${user.first_name}")
                Text("Last Name: ${user.last_name}")
                Text("Avatar: ${user.avatar}")
            }
        }
    }
}
