package com.codexo.todos.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codexo.todos.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllTodos(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(toDoDao: ToDoData)

    @Update
    suspend fun updateTodo(toDoDao: ToDoData)

    @Delete
    suspend fun deleteItem(toDoDao: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()
}