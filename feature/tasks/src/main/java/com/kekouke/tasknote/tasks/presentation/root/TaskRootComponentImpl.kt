package com.kekouke.tasknote.tasks.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.tasks.domain.repository.TaskRepository
import com.kekouke.tasknote.tasks.domain.usecase.GetAvailableStatusesUseCase
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponentImpl
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponentImpl
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponent.Child
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponent.Output
import kotlinx.serialization.Serializable
import javax.inject.Inject

internal class TaskRootComponentImpl @Inject constructor(
    componentContext: ComponentContext,
    private val output: (Output) -> Unit,
    private val taskRepository: TaskRepository
) : TaskRootComponent, ComponentContext by componentContext {

    override val stack: Value<ChildStack<*, Child>>
        get() = _stack

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child = when (config) {
        Config.List -> Child.List(
            TaskListComponentImpl(
                componentContext = context.childContext("task_list"),
                taskRepository = taskRepository,
                getAvailableStatusesUseCase = GetAvailableStatusesUseCase(),
                output = ::onTaskListOutput
            )
        )

        is Config.Details -> Child.Details(
            TaskDetailsComponentImpl(
                componentContext = context.childContext("task_details"),
                taskId = config.id,
                taskRepository = taskRepository,
                output = ::onTaskDetailsOutput
            )
        )
    }

    private fun onTaskListOutput(output: TaskListComponent.Output) {
        when (output) {
            is TaskListComponent.Output.OpenDetails -> navigation.pushNew(Config.Details(output.id))
        }
    }

    private fun onTaskDetailsOutput(output: TaskDetailsComponent.Output) {
        when (output) {
            TaskDetailsComponent.Output.Exit -> navigation.pop()
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data class Details(val id: String) : Config

        @Serializable
        data object List : Config
    }
}
