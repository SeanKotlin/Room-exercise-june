package com.sklinn.roomexercisejune.fragment.add

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sklinn.roomexercisejune.R
import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.data.UserDao
import com.sklinn.roomexercisejune.data.UserDatabase
import com.sklinn.roomexercisejune.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var mViewModel: UserViewModel
    private lateinit var userDao: UserDao


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userDao = UserDatabase.getDatabase(requireContext()).userDao()

        btn_addUser.setOnClickListener {
            insertDatatoDatabase()
        }
    }

    private fun insertDatatoDatabase() {
        val firstName = et_firstName.text.toString()
        val lastName = et_lastName.text.toString()
        val age = et_age.text

        if (inputCheck(firstName, lastName, age)){
            val user = User(
                id = 0,
                firstName = firstName,
                lastName = lastName,
                age = Integer.parseInt(age.toString())
            )

            mViewModel.addUser(user)

            Toast.makeText(requireContext(), "Successfully added the new user", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Fill out please the fields!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName:String, age: Editable?): Boolean{
        return !((TextUtils.isEmpty(firstName)) && (TextUtils.isEmpty(lastName)) && age.isNullOrEmpty())
    }


}