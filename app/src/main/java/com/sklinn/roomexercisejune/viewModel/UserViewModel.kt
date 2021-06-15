package com.sklinn.roomexercisejune.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.data.UserDatabase
import com.sklinn.roomexercisejune.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    application: Application
) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val userRepository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
        readAllData = userRepository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun updateUSer(user: User){
        viewModelScope.launch(Dispatchers.IO) { userRepository.updateUser(user) }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) { userRepository.deleteUser(user) }
    }
}