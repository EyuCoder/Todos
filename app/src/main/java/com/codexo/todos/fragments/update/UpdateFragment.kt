package com.codexo.todos.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codexo.todos.R
import com.codexo.todos.data.models.Priority
import com.codexo.todos.data.models.ToDoData
import com.codexo.todos.data.viewmodels.ToDoViewModel
import com.codexo.todos.fragments.SharedViewModel
import com.codexo.todos.fragments.todo.TodoAdapter
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.update_title_et.setText(args.currentItem.title)
        view.update_description_et.setText(args.currentItem.description)
        view.update_priority_spinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        view.update_priority_spinner.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmDeleteItem()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updateItem() {
        val title = update_title_et.text.toString()
        val description = update_description_et.text.toString()
        val priority = update_priority_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyData(title, description)
        if (validation) {
            val updatedItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(priority),
                description
            )
            mToDoViewModel.updateTodo(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_todoFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill out one of the fields!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun confirmDeleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: '${args.currentItem.title}'",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_todoFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.title}'?")
        builder.setMessage("Are you sure you want to Remove '${args.currentItem.title}'?")
        builder.create().show()
    }

}