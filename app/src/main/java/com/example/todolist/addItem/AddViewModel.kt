package com.example.todolist.addItem

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.database.ToDoListDAO
import com.example.todolist.database.ToDoListData
import com.example.todolist.formatItems
import kotlinx.coroutines.launch

class AddItemViewModel(
private val text: String = "Adicionado",
val database: ToDoListDAO) : ViewModel() {

    private val _navigateToList = MutableLiveData<Boolean?>()

    val navigateToList: LiveData<Boolean?>
        get() = _navigateToList

    fun doneNavigating() {
        _navigateToList.value = null
    }

    fun onSetText(text: String) {
        viewModelScope.launch {
            val newItem = ToDoListData()
            newItem.title = text

            insert(newItem)

            //text = getItemFromDatabase()
            // Setting this state variable to true will alert the observer and trigger navigation.
            _navigateToList.value = true
        }
    }

    private suspend fun insert(item: ToDoListData) {
        database.insert(item)
    }

    private suspend fun getItemFromDatabase(): ToDoListData? {
        var night = database.getItem()
        return night
    }

}