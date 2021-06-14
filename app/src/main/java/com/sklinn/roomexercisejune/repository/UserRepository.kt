package com.sklinn.roomexercisejune.repository

import androidx.lifecycle.LiveData
import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}