package com.codexo.todos.data.repository

import androidx.lifecycle.LiveData
import com.codexo.todos.data.ToDoDao
import com.codexo.todos.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllTodos: LiveData<List<ToDoData>> = toDoDao.getAllTodos()

    suspend fun insertTodo(toDoData: ToDoData){
        toDoDao.insertTodo(toDoData)
    }
}