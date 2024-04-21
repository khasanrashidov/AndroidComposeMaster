                                          // 20230509 Prof. Hong Jeong
// Bare ROOM database without ViewModel and Repository

package com.example.room

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * # ROOM Database
 * ROOM is a persistence library, part of Android Jetpack, that provides an abstraction layer over SQLite.
 * It simplifies database operations and ensures that you're using best practices for accessing the database on a device.
 *
 * Key components of ROOM are:
 * - Database: Contains the database holder and serves as the main access point for the underlying connection to your app's persisted data.
 * - Entity: Represents a table within the database.
 * - DAO (Data Access Object): Contains the methods used for accessing the database.
 *
 * ROOM provides compile-time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.
 * It also supports coroutines and makes it easier to perform database operations on the background thread.
 */

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
    val users: Flow<List<User>> = userDao.getAll()
    val userList = users.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
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
                scope.launch {
                    val newUser = User(
                        uid = 0,
                        firstName = firstNameInput.value.text,
                        lastName = lastNameInput.value.text
                    )
                    userDao.insertAll(newUser)
                }
            }) {
                Text(text = "Add User")
            }
            Button(onClick = {
                scope.launch {
                    val user = userDao.findByName(
                        first = firstNameInput.value.text,
                        last = lastNameInput.value.text
                    )
                    user?.let { userDao.delete(it) }
                }
            }) {
                Text(text = "Delete User")
            }
            Button(onClick = {
                scope.launch {
                    userDao.deleteAll()
                }
            }) {
                Text(text = "Delete All")
            }
        }
        LazyColumn {
            itemsIndexed(userList.value) { _, user ->
                Text(text = "${user.firstName} ${user.lastName}")
            }
        }
    }
}
