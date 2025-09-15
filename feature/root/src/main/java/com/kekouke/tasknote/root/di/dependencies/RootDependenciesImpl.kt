package com.kekouke.tasknote.root.di.dependencies

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory
import javax.inject.Inject

class RootDependenciesImpl @Inject constructor(
    override val loginComponentFactory: LoginComponentFactory
) : RootDependencies
