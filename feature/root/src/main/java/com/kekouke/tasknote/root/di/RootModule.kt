package com.kekouke.tasknote.root.di


import com.kekouke.tasknote.root.di.dependencies.RootDependencies
import com.kekouke.tasknote.root.di.dependencies.RootDependenciesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RootModule {
    @Provides
    fun providesRootComponentDependencies(
        dependencies: RootDependenciesImpl
    ): RootDependencies = dependencies
}
