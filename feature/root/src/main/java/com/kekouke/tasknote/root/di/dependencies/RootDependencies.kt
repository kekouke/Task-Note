package com.kekouke.tasknote.root.di.dependencies

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import com.kekouke.tasknote.root.domain.usecase.GetLaunchScreenUseCase

interface RootDependencies {
    val loginComponentFactory: LoginComponentFactory
    val getLaunchScreenUseCase: GetLaunchScreenUseCase
}
