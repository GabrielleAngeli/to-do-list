package com.example.todolist.addItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.database.ToDoListDAO

class AddItemViewModelFactory(
    private val text: String,
    private val dataSource: ToDoListDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            return AddItemViewModel(text, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}