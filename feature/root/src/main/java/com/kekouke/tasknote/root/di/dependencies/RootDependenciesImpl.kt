package com.kekouke.tasknote.root.di.dependencies

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.root.domain.usecase.GetLaunchScreenUseCase
import com.kekouke.tasknote.tasks.di.factory.TaskRootComponentFactory
import javax.inject.Inject

class RootDependenciesImpl @Inject constructor(
    override val loginComponentFactory: LoginComponentFactory,
    override val tasksComponentFactory: TaskRootComponentFactory,
    override val getLaunchScreenUseCase: GetLaunchScreenUseCase,
    override val userRepository: UserRepository
) : RootDependencies
