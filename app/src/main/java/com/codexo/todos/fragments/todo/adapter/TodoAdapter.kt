package com.codexo.todos.fragments.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codexo.todos.data.models.ToDoData
import com.codexo.todos.databinding.RvItemBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList = emptyList<ToDoData>()

    class TodoViewHolder(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): TodoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvItemBinding.inflate(layoutInflater, parent, false)
                return TodoViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       val currentItem = todoList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(toDoData: List<ToDoData>){
        val notesDiffUtil = NotesDiffUtil(todoList, toDoData)
        val notesDiffResult = DiffUtil.calculateDiff(notesDiffUtil)

        this.todoList = toDoData
        notesDiffResult.dispatchUpdatesTo(this)
    }
}
