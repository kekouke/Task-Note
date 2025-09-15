package com.kekouke.tasknote.tasks.di

import com.kekouke.tasknote.tasks.data.TaskDataRepository
import com.kekouke.tasknote.tasks.data.datasource.TaskDataSource
import com.kekouke.tasknote.tasks.data.remote.TaskNetworkDataSource
import com.kekouke.tasknote.tasks.di.factory.TaskRootComponentFactory
import com.kekouke.tasknote.tasks.domain.repository.TaskRepository
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TasksModule {
    @Binds
    @Singleton
    fun bindTaskDataSource(impl: TaskNetworkDataSource): TaskDataSource

    @Binds
    @Singleton
    fun bindTaskRepository(impl: TaskDataRepository): TaskRepository

    @Binds
    fun bindTaskRootComponentFactory(impl: TaskRootComponentFactory): TaskRootComponent.Factory
}
