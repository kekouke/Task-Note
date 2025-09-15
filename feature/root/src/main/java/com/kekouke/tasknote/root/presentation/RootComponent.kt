package com.kekouke.tasknote.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        data object Login : Child()
        data object Tasks : Child()
    }
}
