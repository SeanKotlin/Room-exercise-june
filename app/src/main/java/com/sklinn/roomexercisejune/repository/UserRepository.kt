package com.sklinn.roomexercisejune.repository

import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.data.UserDao

class UserRepository(
    private val userDao: UserDao
) {
    val readAllData = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}