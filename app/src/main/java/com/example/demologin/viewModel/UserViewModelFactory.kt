package com.example.demologin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demologin.database.UserRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UserViewModelFactory
    @Inject
    constructor(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T

        }

        throw IllegalArgumentException("Unable construct viewmodel")
    }
}