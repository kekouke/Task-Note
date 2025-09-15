package com.kekouke.tasknote.login.di.factory

import com.arkivanov.decompose.ComponentContext
import com.kekouke.tasknote.login.presentation.LoginComponent
import com.kekouke.tasknote.login.presentation.LoginComponent.Output
import com.kekouke.tasknote.login.presentation.PreviewLoginComponent
import javax.inject.Inject

class LoginComponentFactory @Inject constructor(

) : LoginComponent.Factory {
    override fun invoke(
        componentContext: ComponentContext,
        output: (Output) -> Unit
    ): LoginComponent {
        return PreviewLoginComponent()
    }
}
