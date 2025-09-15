package com.kekouke.tasknote.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.kekouke.tasknote.login.presentation.LoginComponent
import com.kekouke.tasknote.root.di.dependencies.RootDependencies
import com.kekouke.tasknote.root.domain.entities.LaunchScreen
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: RootDependencies
) : RootComponent, ComponentContext by componentContext {

    override val stack: Value<ChildStack<*, RootComponent.Child>>
        get() = _stack

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = pickLaunchScreen(),
        handleBackButton = true,
        childFactory = ::createChild
    )

    private val coroutineScope = coroutineScope()

    private fun pickLaunchScreen(): Config {
        return when (dependencies.getLaunchScreenUseCase()) {
            LaunchScreen.Login -> Config.Login
            LaunchScreen.Tasks -> Config.Tasks
        }
    }

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): RootComponent.Child = when (config) {
        is Config.Login -> RootComponent.Child.Login(
            dependencies.loginComponentFactory.invoke(
                componentContext = context.childContext("login"),
                output = ::onLoginOutput
            )
        )

        Config.Tasks -> RootComponent.Child.Tasks
    }

    private fun onLoginOutput(output: LoginComponent.Output) {
        when (output) {
            LoginComponent.Output.LoginCompleted -> navigation.replaceAll(Config.Tasks)
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Login : Config

        @Serializable
        data object Tasks : Config
    }
}
