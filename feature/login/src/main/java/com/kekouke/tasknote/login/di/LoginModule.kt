package com.kekouke.tasknote.login.di

import com.kekouke.tasknote.login.data.UserDataRepository
import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.login.presentation.LoginComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginModule {

    @Binds
    @Singleton
    fun bindUserRepository(impl: UserDataRepository): UserRepository

    @Binds
    fun provideLoginComponentFactory(impl: LoginComponentFactory): LoginComponent.Factory
}
