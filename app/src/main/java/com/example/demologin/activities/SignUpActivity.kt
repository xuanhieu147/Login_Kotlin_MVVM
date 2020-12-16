package com.example.demologin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demologin.R
import com.example.demologin.database.UserDAO
import com.example.demologin.database.UserDatabase
import com.example.demologin.database.UserRepository
import com.example.demologin.databinding.ActivityMainBinding
import com.example.demologin.databinding.ActivitySignUpBinding
import com.example.demologin.viewModel.LoginViewModel
import com.example.demologin.viewModel.UserViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        message()
    }


    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val dao: UserDAO = UserDatabase.getInstance(application).getUserDao()
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.userViewModel = loginViewModel
        binding.lifecycleOwner = this
        displayUserList()


    }

    private fun message() {
        loginViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayUserList() {
        loginViewModel.users.observe(this, Observer {
            Log.d("AAA", it.toString())
        })
    }
}