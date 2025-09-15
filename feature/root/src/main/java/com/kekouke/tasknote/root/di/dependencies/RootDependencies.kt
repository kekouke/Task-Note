package com.kekouke.tasknote.root.di.dependencies

import com.kekouke.tasknote.login.di.factory.LoginComponentFactory

interface RootDependencies {
    val loginComponentFactory: LoginComponentFactory
}
