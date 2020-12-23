package com.example.demologin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demologin.R
import com.example.demologin.database.UserDAO
import com.example.demologin.database.UserDatabase
import com.example.demologin.database.UserRepository
import com.example.demologin.databinding.ActivityMainBinding
import com.example.demologin.di.UserApplication
import com.example.demologin.viewModel.LoginViewModel
import com.example.demologin.viewModel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var loginViewModel: LoginViewModel? = null

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)
        injectDagger()
        init()
        message()
        displayUserList()
        click()


    }

    private fun injectDagger() {
        UserApplication.instance.userComponent.inject(this)
    }

    private fun click() {
        binding?.btnSignUp?.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding?.btnLogin?.setOnClickListener {
            loginViewModel?.loginUser(
                binding?.edEmail?.text.toString(),
                binding?.edPassWord?.text.toString()
            )
                ?.observe(this, Observer { loginUser ->
                    if (binding?.edEmail?.text == null) {
                        Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()


                    } else if (binding?.edPassWord?.text == null) {
                        Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()


                    } else if (!Patterns.EMAIL_ADDRESS.matcher(binding?.edEmail?.text).matches()) {
                        Toast.makeText(
                            this,
                            "Please enter a correct email address",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (loginUser != null) {

                        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                        val mainIntent = Intent(this, UserActivity::class.java)
                        startActivity(mainIntent)
                    } else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                        Log.d("AAA", loginUser.toString())
                    }

                })
        }
    }

    private fun message() {
        loginViewModel?.message?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loginViewModel =
            ViewModelProvider(this, userViewModelFactory).get(LoginViewModel::class.java)
        binding?.userViewModel = loginViewModel
        binding?.lifecycleOwner = this
    }

    private fun displayUserList() {
         loginViewModel?.users?.observe(this, Observer {
          Log.d("AAA", it.toString())
         })
    }
}