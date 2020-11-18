package com.codexo.todos.fragments.todo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codexo.todos.R
import kotlinx.android.synthetic.main.fragment_todo.view.*

class TodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addFragment)
        }
        view.todo_layout.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo_fragment_todo, menu)
    }
}