package com.example.flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItemViewModel : ViewModel() {
    val items: Flow<List<String>> = flow {
        emit(fetchItems())
    }

    private fun fetchItems(): List<String> {
        // Simulate an API call
        return listOf("Item 1", "Item 2", "Item 3")
    }
}
