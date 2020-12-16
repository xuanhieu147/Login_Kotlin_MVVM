package com.example.demologin.viewModel

import android.content.Context
import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.demologin.activities.SignUpActivity
import com.example.demologin.database.UserDAO
import com.example.demologin.database.UserRepository
import com.example.demologin.model.User
import kotlinx.coroutines.launch
import com.example.demologin.viewModel.LoginViewModel as LoginViewModel1
import com.example.demologin.viewModel.LoginViewModel as th

class LoginViewModel(private val repository: UserRepository) : ViewModel(), Observable {

    val users = repository.users

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage


    fun saveUser() {
        if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter email")

        } else if (inputPassword.value == null) {
            statusMessage.value = Event("Please enter name")

        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("Please enter a correct email address")
        } else {
            val name: String = inputEmail.value!!
            val password: String = inputPassword.value!!
            insertUser(User(0, name, password))
            inputEmail.value = null
            inputPassword.value = null
        }
    }

    fun login() {

//        if (inputEmail.value == null) {
//            statusMessage.value = Event("Please enter email")
//
//        } else if (inputPassword.value == null) {
//            statusMessage.value = Event("Please enter name")
//
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
//            statusMessage.value = Event("Please enter a correct email address")
//
//        } else {
//            val email: String = inputEmail.value!!
//            val password: String = inputPassword.value!!
//            loginUser(email, password).observe(this, Observer { loginUser ->
//                if (loginUser != null) {
//
//                    statusMessage.value = Event("Login Success")
//                    inputEmail.value = null
//                    inputPassword.value = null
//                } else {
//                    statusMessage.value = Event("Login Fail")
//
//                }
//            })
//
//        }

    }

    fun loginUser(email: String, password: String): LiveData<User> {
        return repository.login(email, password)
    }


    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deletetUser(user)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}



