package com.sklinn.roomexercisejune.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.data.UserDatabase
import com.sklinn.roomexercisejune.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    private val readAllData: LiveData<List<User>>

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
        readAllData = userRepository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { userRepository.addUser(user) }
    }
}