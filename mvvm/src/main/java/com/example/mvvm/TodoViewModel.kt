package com.example.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _todoItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoItems: StateFlow<List<TodoItem>> = _todoItems

    init {
        loadTodoItems()
    }

    private fun loadTodoItems() {
        _todoItems.value = todoRepository.getTodoItems()
    }

    fun addTodoItem(item: TodoItem) {
        viewModelScope.launch {
            todoRepository.addTodoItem(item)
            loadTodoItems()
        }
    }

    fun deleteTodoItem(item: TodoItem) {
        viewModelScope.launch {
            todoRepository.deleteTodoItem(item)
            loadTodoItems()
        }
    }

    fun deleteSelectedItems(items: List<TodoItem>) {
        viewModelScope.launch {
            _todoItems.value = _todoItems.value.filter { it !in items }
            items.forEach { item ->
                todoRepository.deleteTodoItem(item)
            }
        }
    }
}
