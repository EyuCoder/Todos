package com.codexo.todos.fragments.todo

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codexo.todos.R
import com.codexo.todos.data.viewmodels.ToDoViewModel
import com.codexo.todos.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_todo.view.*

class TodoFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        val recyclerView = view.todo_rv

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllTodos.observe(viewLifecycleOwner, Observer{data ->
            mSharedViewModel.checkIfDbEmpty(data)
            adapter.setData(data)
        })
        mSharedViewModel.emptyDb.observe(viewLifecycleOwner, Observer {
            showEmptyDbViews(it)
        })


        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun showEmptyDbViews(emptyDb: Boolean) {
        if (emptyDb){
            view?.no_data_iv?.visibility = View.VISIBLE
            view?.no_data_txt?.visibility = View.VISIBLE
        }else{
            view?.no_data_iv?.visibility = View.INVISIBLE
            view?.no_data_txt?.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo_fragment_todo, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete_all -> confirmDeleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully Removed All Notes!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete All notes?")
        builder.setMessage("Are you sure you want to Remove everything?")
        builder.create().show()
    }
}