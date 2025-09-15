package com.kekouke.tasknote.login.di

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import com.kekouke.tasknote.login.presentation.LoginComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginModule {

    @Binds
    fun provideLoginComponentFactory(impl: LoginComponentFactory): LoginComponent.Factory
}
