package com.example.todolist.list

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.database.ToDoListDAO
import com.example.todolist.database.ToDoListData
import com.example.todolist.formatItems
import kotlinx.coroutines.launch

class TodoListViewModel(
    val database: ToDoListDAO, application: Application
) : AndroidViewModel(application) {

    var item = MutableLiveData<ToDoListData?>()

    val items = database.getAll()

    val itemsString = Transformations.map(items) { items ->
        formatItems(items, application.resources)
    }

    private val _navigateToAddItem = MutableLiveData<Boolean?>()

    val navigateToAddItem: LiveData<Boolean?>
        get() = _navigateToAddItem


    init {
        initializeItem()
    }

    private fun initializeItem() {
        viewModelScope.launch {
            item.value = getItemFromDatabase()
        }
    }

    private suspend fun getItemFromDatabase(): ToDoListData? {
        return database.getItem()
    }

    fun doneNavigating() {
        _navigateToAddItem.value = null
    }

    fun onStart() {
        viewModelScope.launch {
            _navigateToAddItem.value = true
        }
    }

    fun onDelete(itemId: Long) {
        viewModelScope.launch {
            database.delete(itemId)
        }
    }

}