package com.kekouke.tasknote.root.di.dependencies

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.root.domain.usecase.GetLaunchScreenUseCase
import com.kekouke.tasknote.tasks.di.factory.TaskRootComponentFactory

interface RootDependencies {
    val loginComponentFactory: LoginComponentFactory
    val tasksComponentFactory: TaskRootComponentFactory
    val getLaunchScreenUseCase: GetLaunchScreenUseCase
    val userRepository: UserRepository
}
