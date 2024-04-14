package com.example.roomviewmodel

import android.os.Bundle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel

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

/**
 *  # Instantiate UserViewModel directly
 *      Not recommended because it does not take advantage of the lifecycle-aware capabilities
 *      provided by the viewModel() function.
 *      val viewModel = remember { UserViewModel(userDao) }
 *
 *  # Instantiate UserViewModel by Factory
 *     Using a ViewModel factory allows you to create ViewModels with custom constructor parameters
 *      and manage their dependencies more easily. This is especially useful when working
 *      with dependency injection frameworks like Hilt or Dagger, as it simplifies passing
 *      dependencies to your ViewModel instances.
 *
 */

    val viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(userDao))

    val userList by viewModel.users.collectAsState(initial = emptyList())
    val firstNameInput = remember { mutableStateOf(TextFieldValue("")) }
    val lastNameInput = remember { mutableStateOf(TextFieldValue("")) }

    Column {
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
        }
        LazyColumn {
            itemsIndexed(userList) { _, user ->
                Text(text = "${user.firstName} ${user.lastName}")
            }
        }
    }
}

