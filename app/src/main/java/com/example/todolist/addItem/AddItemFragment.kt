package com.example.todolist.addItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.ToDoListDatabase
import com.example.todolist.databinding.AddItemFragmentBinding
import com.example.todolist.list.TodoListViewModelFactory

class AddItemFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: AddItemFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.add_item_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ToDoListDatabase.getInstance(application)?.toDoListDao
        val viewModelFactory = dataSource?.let { AddItemViewModelFactory("eu", dataSource) }

        val addItemViewModel =
            viewModelFactory?.let {
                ViewModelProvider(
                    this, it
                ).get(AddItemViewModel::class.java)
            }

        binding.addItemViewModel = addItemViewModel

        addItemViewModel?.navigateToList?.observe(this, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    AddItemFragmentDirections.actionAddItemFragmentToListFragment()
                )
                addItemViewModel.doneNavigating()
            }
        })

        binding.addList.setOnClickListener {
            addItemViewModel?.onSetText(binding.addTitle.text.toString())
        }

        return binding.root
    }


}