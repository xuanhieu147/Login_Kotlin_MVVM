package com.example.demologin.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demologin.model.User


class UserRepository(private val userDao: UserDAO) {

    val users = userDao.getAllUser()
    var loginDatabase : UserDatabase? = null

    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun updateUser(user: User): Int {
        return userDao.updateUser(user)
    }

    suspend fun deletetUser(user: User): Int {
        return userDao.deleteUser(user)
    }


    fun getAllUser(): LiveData<List<User>> = userDao.getAllUser()

    fun login(email: String, password: String): LiveData<User> {
        val user =userDao?.getUser(email, password)
        return user
    }

}