package com.kekouke.tasknote.tasks.di.factory

import com.arkivanov.decompose.ComponentContext
import com.kekouke.tasknote.tasks.domain.repository.TaskRepository
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponent
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponentImpl
import javax.inject.Inject

class TaskRootComponentFactory @Inject constructor(
    private val taskRepository: TaskRepository
) : TaskRootComponent.Factory {
    override fun invoke(
        componentContext: ComponentContext,
        output: (TaskRootComponent.Output) -> Unit
    ): TaskRootComponent {
        return TaskRootComponentImpl(
            componentContext = componentContext,
            output = output,
            taskRepository = taskRepository
        )
    }
}
