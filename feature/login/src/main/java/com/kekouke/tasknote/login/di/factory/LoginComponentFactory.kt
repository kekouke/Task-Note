package com.kekouke.tasknote.login.di.factory

import com.arkivanov.decompose.ComponentContext
import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.login.presentation.LoginComponent
import com.kekouke.tasknote.login.presentation.LoginComponent.Output
import com.kekouke.tasknote.login.presentation.LoginComponentImpl
import dagger.Lazy
import javax.inject.Inject

class LoginComponentFactory @Inject constructor(
    private val userRepository: Lazy<UserRepository>
) : LoginComponent.Factory {
    override fun invoke(
        componentContext: ComponentContext,
        output: (Output) -> Unit
    ): LoginComponent {
        return LoginComponentImpl(
            componentContext = componentContext,
            output = output,
            userRepository = userRepository.get()
        )
    }
}
