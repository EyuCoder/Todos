package com.codexo.todos.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codexo.todos.R
import com.codexo.todos.data.models.Priority
import com.codexo.todos.data.models.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val emptyDb: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDbEmpty(toDoData: List<ToDoData> ){
        emptyDb.value = toDoData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object:
    AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor( application, R.color.red))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor( application, R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor( application, R.color.green))}
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}

    }

    fun parsePriority(priority: String): Priority {

        return when(priority){
            "High Priority"-> Priority.HIGH
            "Medium Priority"-> Priority.MEDIUM
            "Low Priority"-> Priority.LOW
            else -> Priority.LOW
        }
    }

    fun parsePriorityToInt(priority: Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }

    fun verifyData(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) && TextUtils.isEmpty(description)) {
            false
        }else !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}