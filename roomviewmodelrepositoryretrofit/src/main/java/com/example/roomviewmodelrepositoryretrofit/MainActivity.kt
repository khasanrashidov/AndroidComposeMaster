package com.example.roomviewmodelrepositoryretrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val db = UserRoomDatabase.getDatabase(applicationContext)
                    MyApp(db)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(db: UserRoomDatabase) {
    val userDao = db.userDao()
    Log.d("MyApp", "userDao: $userDao")
    val userRepository = UserRepository(userDao)
    Log.d("MyApp", "userRepository: $userRepository")
    val viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(userRepository))

    val roomUserList by viewModel.roomUsers.collectAsState(initial = emptyList())
//    val retrofitUserList by viewModel.retrofitUsers.collectAsState(initial = emptyList())
//    val githubUser by viewModel.githubUsers.collectAsState(initial = null)
    val firstNameInput = remember { mutableStateOf(TextFieldValue("")) }
    val lastNameInput = remember { mutableStateOf(TextFieldValue("")) }
    val githubUsernameInput = remember { mutableStateOf(TextFieldValue("")) }


    Column {
        Text(text = "Room Users:")
        LazyColumn {
            itemsIndexed(roomUserList) { _, user ->
                Text(text = "${user.firstName} ${user.lastName}")
            }
        }
//        Text(text = "Retrofit Users:")
//        LazyColumn {
//            itemsIndexed(retrofitUserList) { _, user ->
//                Text(text = "${user.firstName} ${user.lastName}")
//            }
//        }
//        Text(text = "GitHub User:")
//        githubUser?.let { user ->
//            Text(text = "${user.login} - ${user.name}")
//        }
        Row {
            TextField(
                value = firstNameInput.value,
                onValueChange = { firstNameInput.value = it },
                label = { Text("First Name") }
            )
            TextField(
                value = lastNameInput.value,
                onValueChange = { lastNameInput.value = it },
                label = { Text("Last Name") }
            )
        }
        Row {
            TextField(
                value = githubUsernameInput.value,
                onValueChange = { githubUsernameInput.value = it },
                label = { Text("GitHub Username") }
            )
        }
        Row {
            Button(onClick = {
                viewModel.insertUser(
                    firstName = firstNameInput.value.text,
                    lastName = lastNameInput.value.text
                )
            }) {
                Text(text = "Add User")
            }
            Button(onClick = {
                viewModel.deleteUser(
                    firstName = firstNameInput.value.text,
                    lastName = lastNameInput.value.text
                )
            }) {
                Text(text = "Delete User")
            }
            Button(onClick = {
                viewModel.deleteAllUsers()
            }) {
                Text(text = "Delete All")
            }
//            Button(onClick = {
//                viewModel.fetchRetrofitUsers()
//            }) {
//                Text(text = "Fetch Retrofit Users")
//            }
//            Button(onClick = {
//                viewModel.fetchGithubUser(githubUsernameInput.value.text)
//            }) {
//                Text(text = "Fetch GitHub User")
//            }
        }
    }
}
