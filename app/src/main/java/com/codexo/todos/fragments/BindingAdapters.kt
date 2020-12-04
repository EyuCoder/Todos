package com.codexo.todos.fragments

import android.os.Build
import android.view.View
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.codexo.todos.R
import com.codexo.todos.data.models.Priority
import com.codexo.todos.data.models.ToDoData
import com.codexo.todos.fragments.todo.TodoFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object {

        @BindingAdapter("android:navToAddFragment")
        @JvmStatic
        fun navToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_todoFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDb")
        @JvmStatic
        fun emptyDb(view: View, emptyDb: MutableLiveData<Boolean>) {
            when (emptyDb.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority){
            when(priority){
                Priority.HIGH -> view.setSelection(0)
                Priority.MEDIUM -> view.setSelection(1)
                Priority.LOW -> view.setSelection(2)
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority){
            when(priority){
                Priority.HIGH -> cardView.setBackgroundResource(R.color.red)
                Priority.MEDIUM -> cardView.setBackgroundResource(R.color.yellow)
                Priority.LOW -> cardView.setBackgroundResource(R.color.green)
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: ToDoData){
            view.setOnClickListener{
                val action = TodoFragmentDirections.actionTodoFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
    }
}