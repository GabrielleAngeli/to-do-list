package com.example.todolist.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoListData::class], version = 1, exportSchema = false)
abstract class ToDoListDatabase : RoomDatabase() {

    abstract val toDoListDao: ToDoListDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoListDatabase? = null

        fun getInstance(context: Context): ToDoListDatabase? {
            synchronized(ToDoListDatabase::class) {
                var instance = INSTANCE
                if (INSTANCE == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoListDatabase::class.java, "todolistdb"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }


    }

}