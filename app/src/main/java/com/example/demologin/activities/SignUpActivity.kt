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
import com.example.demologin.di.UserApplication
import com.example.demologin.viewModel.LoginViewModel
import com.example.demologin.viewModel.UserViewModelFactory
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
        init()
        message()
    }

    private fun injectDagger() {
        UserApplication.instance.userComponent.inject(this)
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        loginViewModel =
            ViewModelProvider(this, userViewModelFactory).get(LoginViewModel::class.java)
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