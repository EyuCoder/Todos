package com.codexo.todos.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.codexo.todos.data.ToDoDatabase
import com.codexo.todos.data.models.ToDoData
import com.codexo.todos.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: ToDoRepository

    val getAllTodos: LiveData<List<ToDoData>>

    init {
        repository = ToDoRepository(toDoDao)
        getAllTodos = repository.getAllTodos
    }

    fun insertTodo(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertTodo(toDoData)
        }
    }

    fun updateTodo(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateTodo(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
}