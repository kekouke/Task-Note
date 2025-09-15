package com.kekouke.tasknote.tasks.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent

interface TaskRootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class List(val component: TaskListComponent) : Child()
        data class Details(val component: TaskDetailsComponent) : Child()
    }

    sealed interface Output

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            output: (Output) -> Unit
        ): TaskRootComponent
    }
}
