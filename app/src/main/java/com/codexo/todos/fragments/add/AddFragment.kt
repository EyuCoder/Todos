package com.codexo.todos.fragments.add

import android.os.Bundle
import android.text.TextUtils.*
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codexo.todos.R
import com.codexo.todos.data.models.Priority
import com.codexo.todos.data.models.ToDoData
import com.codexo.todos.data.viewmodels.ToDoViewModel
import com.codexo.todos.fragments.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private val mToDoModelView: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.priority_spinner.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            insertTodo()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertTodo() {
        val mTitle = title_et.text.toString()
        val mPriority = priority_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()
        
        val validate = mSharedViewModel.verifyData(mTitle, mDescription)

        if (validate){
            val newTodo = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoModelView.insertTodo(newTodo)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_todoFragment)
        }else Toast.makeText(requireContext(), "Please insert a title!", Toast.LENGTH_SHORT).show()
    }
}