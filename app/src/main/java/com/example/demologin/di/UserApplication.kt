package com.example.demologin.di

import android.app.Application

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