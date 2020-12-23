package com.example.demologin.di

import android.app.Application
import com.example.demologin.di.component.UserComponent
import com.example.demologin.di.module.RoomDatabaseModule

class UserApplication : Application() {

    companion object {
        lateinit var instance: UserApplication
    }
    lateinit var userComponent: UserComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        userComponent = DaggerUserComponent
            .builder()
            .roomDatabaseModule(RoomDatabaseModule(this))
            .build()
    }
}