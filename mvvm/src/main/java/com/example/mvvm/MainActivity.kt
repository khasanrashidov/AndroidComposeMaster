package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm.ui.theme.ComposeMasterTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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
                    val viewModel: TodoViewModel = viewModel()
                    TodoApp(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(viewModel: TodoViewModel) {
    val items by viewModel.todoItems.collectAsState(emptyList())
    val newItemText = remember { mutableStateOf("") }
    val selectedItems = remember { mutableStateListOf<TodoItem>() }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = newItemText.value,
            onValueChange = { newItemText.value = it },
            label = { Text("New to-do item") }
        )
        Button(
            onClick = {
                viewModel.addTodoItem(
                    TodoItem(
                        id = System.currentTimeMillis().toInt(),
                        text = newItemText.value
                    )
                )
                newItemText.value = ""
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add")
        }
        Text("To-Do Items:", modifier = Modifier.padding(top = 16.dp))
        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp)
                .weight(1f)
        ) {
            items(
                items = items,
                key = { item -> item.id }
            ) { item ->
                val selected = selectedItems.contains(item)
                TodoItemView(
                    item,
                    selected,
                    onSelect = {
                        if (selected) {
                            selectedItems.remove(item)
                        } else {
                            selectedItems.add(item)
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                viewModel.deleteSelectedItems(selectedItems.toList())
                selectedItems.clear()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Delete Selected Items")
        }
    }
}


@Composable
fun TodoItemView(
    item: TodoItem,
    selected: Boolean,
    onSelect: (TodoItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(if (selected) Color.LightGray else Color.Transparent)
            .clickable { onSelect(item) }
    ) {
        Text(text = item.text)
    }
}

