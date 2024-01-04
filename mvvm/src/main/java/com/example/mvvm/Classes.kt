package com.example.mvvm

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application()

// TodoItem.kt
data class TodoItem(val id: Int, val text: String)

// TodoRepository.kt
interface TodoRepository {
    fun getTodoItems(): List<TodoItem>
    fun addTodoItem(item: TodoItem)
    fun deleteTodoItem(item: TodoItem)
}


class TodoRepositoryImpl @Inject constructor() : TodoRepository {
    private val todoItems = mutableListOf<TodoItem>()

    override fun getTodoItems(): List<TodoItem> {
        return todoItems
    }

    override fun addTodoItem(item: TodoItem) {
        todoItems.add(item)
    }

    override fun deleteTodoItem(item: TodoItem) {
        todoItems.remove(item)
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindTodoRepository(impl: TodoRepositoryImpl): TodoRepository
}
