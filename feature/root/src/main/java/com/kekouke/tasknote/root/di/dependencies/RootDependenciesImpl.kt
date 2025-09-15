package com.kekouke.tasknote.root.di.dependencies

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import com.kekouke.tasknote.root.domain.usecase.GetLaunchScreenUseCase
import javax.inject.Inject

class RootDependenciesImpl @Inject constructor(
    override val loginComponentFactory: LoginComponentFactory,
    override val getLaunchScreenUseCase: GetLaunchScreenUseCase
) : RootDependencies
