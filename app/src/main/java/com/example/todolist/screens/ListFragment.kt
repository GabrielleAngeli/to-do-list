package com.example.todolist.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private lateinit var viewModel: TodoListViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<ListFragmentBinding>(inflater,
            R.layout.list_fragment,container,false)

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)


        binding.addButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_list_Fragment_to_add_item_fragment)
        }
        return binding.root
    }

}