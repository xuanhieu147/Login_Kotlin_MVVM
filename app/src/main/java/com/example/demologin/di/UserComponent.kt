package com.example.demologin.di

import com.example.demologin.activities.MainActivity
import com.example.demologin.activities.SignUpActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomDatabaseModule::class,UserModule::class])
interface UserComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(signUpActivity: SignUpActivity)
}