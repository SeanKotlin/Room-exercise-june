package com.sklinn.roomexercisejune.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sklinn.roomexercisejune.R
import com.sklinn.roomexercisejune.adapter.Adapter
import com.sklinn.roomexercisejune.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var mViewModel: UserViewModel
    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        adapter = Adapter()

        fab_addUser.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        recycerview_listUser.adapter = adapter
        recycerview_listUser.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        mViewModel.readAllData.observe(viewLifecycleOwner, Observer { users->
            adapter.setNewData(users)
        })

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId ==R.id.delete_menu){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser(){
        AlertDialog.Builder(requireContext())
            .setNegativeButton("No"){_,_ -> }
            .setPositiveButton("Yes"){_,_ ->
                mViewModel.deleteAllUsers()
                Toast.makeText(requireContext(), "Successfully delete everything", Toast.LENGTH_LONG).show()
            }
            .setMessage("Are you sure to clean everything")
            .setTitle("DELETE EVERYTHING")
            .create().show()
    }
}