package com.example.demologin.di

import com.example.demologin.database.UserDatabase
import com.example.demologin.database.UserRepository
import com.example.demologin.viewModel.UserViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class UserModule {
    @Singleton
    @Provides
    fun providesBookRepository(userDatabase: UserDatabase): UserRepository {
        return UserRepository(userDatabase)
    }
}