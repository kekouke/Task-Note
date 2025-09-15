package com.kekouke.tasknote.tasks.presentation.list

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.UiState
import com.kekouke.tasknote.tasks.presentation.list.models.TaskUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewTaskListComponentImpl : TaskListComponent {

    override val uiState: Value<UiState>
        get() = MutableValue(
            UiState(
                isLoading = false,
                tasks = List(6) {
                    TaskUiModel.preview().copy(id = it.toString())
                }
            )
        )

    override val showTaskStatusModal: StateFlow<TaskListComponent.ModalConfig<TaskListComponent.TaskStatusModalData>>
        get() = MutableStateFlow(TaskListComponent.ModalConfig())

    override val showNewTaskModal: StateFlow<TaskListComponent.ModalConfig<TaskListComponent.NewTaskModalData>>
        get() = MutableStateFlow(TaskListComponent.ModalConfig())

    override fun onEvent(event: TaskListComponent.Event) = Unit
}