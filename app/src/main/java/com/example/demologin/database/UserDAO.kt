package com.example.demologin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demologin.model.User

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User): Int

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("select * from user_table")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM user_table where email_col= :email and password_col= :password")
    fun getUser(email: String, password: String): LiveData<User>
}