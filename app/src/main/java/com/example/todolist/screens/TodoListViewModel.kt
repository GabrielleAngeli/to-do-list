package com.example.todolist.screens

import android.util.Log
import androidx.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {
    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}