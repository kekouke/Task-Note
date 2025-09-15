package com.kekouke.tasknote.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.login.presentation.LoginComponent
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class Login(val component: LoginComponent) : Child()
        data class Tasks(val component: TaskRootComponent) : Child()
    }
}
