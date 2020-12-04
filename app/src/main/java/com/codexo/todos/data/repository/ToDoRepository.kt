package com.codexo.todos.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.codexo.todos.data.ToDoDao
import com.codexo.todos.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllTodos: LiveData<List<ToDoData>> = toDoDao.getAllTodos()
    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    suspend fun insertTodo(toDoData: ToDoData){
        toDoDao.insertTodo(toDoData)
    }

    suspend fun updateTodo(toDoData: ToDoData){
        toDoDao.updateTodo(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
        return toDoDao.searchDatabase(searchQuery)
    }
}