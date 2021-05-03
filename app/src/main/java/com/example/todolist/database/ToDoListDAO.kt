package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoListDAO {
    @Query("SELECT * from todolist")
    fun getAll(): LiveData<List<ToDoListData>>

    @Insert
    suspend fun insert(toDoListData: ToDoListData)

    @Query("DELETE From todolist where id = :id")
    suspend fun delete(id : Long)

    @Query("SELECT * from todolist WHERE id = :id")
    suspend fun get(id: Long): ToDoListData?

    @Update
    suspend  fun update(night: ToDoListData)

    @Query("SELECT * FROM todolist ORDER BY id DESC LIMIT 1")
    suspend fun getItem(): ToDoListData?
}