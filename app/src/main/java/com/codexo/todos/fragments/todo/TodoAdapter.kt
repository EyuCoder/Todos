package com.codexo.todos.fragments.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codexo.todos.R
import com.codexo.todos.data.models.Priority
import com.codexo.todos.data.models.ToDoData
import kotlinx.android.synthetic.main.rv_item.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList = emptyList<ToDoData>()

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.title_txt.text = todoList[position].title
        holder.itemView.description_txt.text = todoList[position].description

        when (todoList[position].priority) {
            Priority.HIGH -> holder.itemView.priority_label.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red
                )
            )
            Priority.MEDIUM -> holder.itemView.priority_label.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.yellow
                )
            )
            Priority.LOW -> holder.itemView.priority_label.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green
                )
            )
        }

        holder.itemView.item_layout.setOnClickListener{
            val action = TodoFragmentDirections.actionTodoFragmentToUpdateFragment(todoList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(toDoData: List<ToDoData>){
        this.todoList = toDoData
        notifyDataSetChanged()
    }
}
