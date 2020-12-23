package com.example.demologin.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demologin.database.UserDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
class RoomDatabaseModule(application: Application) {
    private var userApplication = application
    private lateinit var userDatabase: UserDatabase
    private val databaseCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

        }
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): UserDatabase {
        userDatabase = Room.databaseBuilder(userApplication, UserDatabase::class.java, "UserDatabase")
            .fallbackToDestructiveMigration()
            .addCallback(databaseCallback)
            .build()
        return userDatabase
    }

    @Singleton
    @Provides
    fun providesCategoryDAO(userDatabase: UserDatabase) = userDatabase.getUserDao()
}