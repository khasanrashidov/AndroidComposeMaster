// 20230509 Prof. Hone Jeong
// ViewModel
// LiveData/MutableLiveData + observeAsState vs. Flow/MutableStateFlow + collectAsState

package com.example.retrofitviewmodel

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitviewmodel.ui.theme.ROOMTheme

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
    // Providing the UsersViewModelFactory
    val reqResApi = RetrofitInstance.api
    val viewModelFactory = UsersViewModelFactory(reqResApi)

    // Using the provided factory to get an instance of UsersViewModel
    val viewModel: UsersViewModel = viewModel(factory = viewModelFactory)
    val users by viewModel.users.collectAsState(emptyList())

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FetchUsersScreen()
}
