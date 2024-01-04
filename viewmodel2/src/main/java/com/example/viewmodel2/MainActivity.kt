package com.example.viewmodel2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel2.ui.theme.ComposeMasterTheme


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
                    UsersListScreen()
                }
            }
        }
    }
}

@Composable
fun UsersListScreen(usersViewModel: UsersViewModel = viewModel()) {
    Column {
        Button(onClick = { usersViewModel.addUser("New User ${usersViewModel.users.value?.size}") }) {
            Text("Add User")
        }

        UsersList(users = usersViewModel.users)
    }
}

@Composable
fun UsersList(users: LiveData<List<User>>) {
    val userList by users.observeAsState(emptyList())

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(userList) { user ->
            Text(text = "${user.id}. ${user.name}")
        }
    }
}
