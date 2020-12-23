package com.example.demologin.di.component

import com.example.demologin.activities.MainActivity
import com.example.demologin.activities.SignUpActivity
import com.example.demologin.di.module.RoomDatabaseModule
import com.example.demologin.di.module.UserModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomDatabaseModule::class, UserModule::class])
interface UserComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(signUpActivity: SignUpActivity)
}