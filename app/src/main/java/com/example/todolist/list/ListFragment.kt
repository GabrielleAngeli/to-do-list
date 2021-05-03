package com.example.todolist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.addItem.AddItemFragmentDirections
import com.example.todolist.database.ToDoListDatabase
import com.example.todolist.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private lateinit var viewModel: TodoListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.list_fragment, container, false)


        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = ToDoListDatabase.getInstance(application)?.toDoListDao
        val viewModelFactory = dataSource?.let { TodoListViewModelFactory(it, application) }

        // Get a reference to the ViewModel associated with this fragment.
        val todoListViewModel =
            viewModelFactory?.let {
                ViewModelProvider(
                    this, viewModelFactory
                ).get(TodoListViewModel::class.java)
            }

        binding.todoListViewModel = todoListViewModel
        binding.lifecycleOwner = this

        todoListViewModel?.navigateToAddItem?.observe(this, Observer { item ->
            item?.let {
                this.findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToAddItemFragment())
                todoListViewModel.doneNavigating()
            }
        })

        val adapter = TodoListAdapter(TodoListListener { itemId ->
            Toast.makeText(context, "$itemId", Toast.LENGTH_LONG).show()
            todoListViewModel?.onDelete(itemId)
        })

        binding.todoList.adapter = adapter

        todoListViewModel?.items?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}