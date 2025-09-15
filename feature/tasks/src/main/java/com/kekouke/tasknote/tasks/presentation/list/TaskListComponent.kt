package com.kekouke.tasknote.tasks.presentation.list

import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import com.kekouke.tasknote.tasks.presentation.list.models.TaskUiModel
import kotlinx.coroutines.flow.StateFlow

interface TaskListComponent {

    val uiState: Value<UiState>

    val showTaskStatusModal: StateFlow<ModalConfig<TaskStatusModalData>>
    val showNewTaskModal: StateFlow<ModalConfig<NewTaskModalData>>

    fun onEvent(event: Event)

    data class UiState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val tasks: List<TaskUiModel> = emptyList()
    )

    data class ModalConfig<T : Any>(val data: T? = null)
    data class TaskStatusModalData(val taskId: String, val nextStatuses: List<TaskStatus>)
    data object NewTaskModalData

    sealed interface Event {
        data object NewTaskClicked : Event
        data object HideAddTaskModal : Event
        data class AddTask(val form: NewTask) : Event
        data class DeleteClicked(val taskId: String) : Event
        data class UpdateStatusClicked(val taskId: String, val status: TaskStatus) : Event
        data class TaskStatusClicked(val task: TaskUiModel) : Event
        data object HideStatusModal : Event
        data class TaskClicked(val taskId: String) : Event
        data object RetryClicked : Event
    }

    sealed interface Output {
        data class OpenDetails(val id: String) : Output
    }
}
