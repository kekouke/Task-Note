package com.kekouke.tasknote.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.kekouke.tasknote.root.di.dependencies.RootDependencies
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
        initialConfiguration = Config.Tasks,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private val coroutineScope = coroutineScope()

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): RootComponent.Child = when (config) {
        Config.Login -> RootComponent.Child.Login
        Config.Tasks -> RootComponent.Child.Tasks
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Login : Config

        @Serializable
        data object Tasks : Config
    }
}
