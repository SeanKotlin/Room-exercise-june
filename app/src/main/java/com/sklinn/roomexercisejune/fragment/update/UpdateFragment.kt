package com.sklinn.roomexercisejune.fragment.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sklinn.roomexercisejune.R
import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.viewModel.UserViewModel
import kotlinx.android.synthetic.main.custom_rows.*
import kotlinx.android.synthetic.main.fragment_update.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment(R.layout.fragment_update) {
    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var mViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        et_age.setText(args.currentUser.age.toString())
        et_firstName.setText(args.currentUser.firstName)
        et_lastName.setText(args.currentUser.lastName)

        btn_updateUser.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val firstName = et_firstName.text.toString()
        val lastName = et_lastName.text.toString()
        val age = et_age.text

        val newUser = User(
            id = args.currentUser.id,
            firstName = firstName,
            lastName = lastName,
            age = Integer.parseInt(age.toString())
        )
        if (inputCheck(firstName, lastName, age)){
            mViewModel.updateUSer(newUser)
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(requireContext(), "Not Successfully Update", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(
        fName: String,
        lName: String,
        age: Editable?
    ): Boolean{
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && age.isNullOrEmpty())
    }

}