package com.kekouke.tasknote.tasks.presentation.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.domain.repository.TaskRepository
import com.kekouke.tasknote.tasks.domain.usecase.GetAvailableStatusesUseCase
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.AddTask
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.DeleteClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.HideAddTaskModal
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.HideStatusModal
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.NewTaskClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.RetryClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.TaskClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.TaskStatusClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.UpdateStatusClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.ModalConfig
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.NewTaskModalData
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Output
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.TaskStatusModalData
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.UiState
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import com.kekouke.tasknote.tasks.presentation.list.models.toUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class TaskListComponentImpl(
    componentContext: ComponentContext,
    private val taskRepository: TaskRepository,
    private val getAvailableStatusesUseCase: GetAvailableStatusesUseCase,
    private val output: (Output) -> Unit
) : TaskListComponent, ComponentContext by componentContext {

    override val uiState: Value<UiState>
        get() = _uiState

    override val showTaskStatusModal = MutableStateFlow(ModalConfig<TaskStatusModalData>())

    override val showNewTaskModal = MutableStateFlow(ModalConfig<NewTaskModalData>())

    private val _uiState = MutableValue(UiState())

    private var tasksFlowJob: Job? = null

    private val coroutineScope = coroutineScope()

    init {
        loadTasks()
    }

    override fun onEvent(event: Event) {
        when (event) {
            is NewTaskClicked -> {
                showNewTaskModal.value = ModalConfig(data = NewTaskModalData)
            }

            is HideAddTaskModal -> {
                showNewTaskModal.value = ModalConfig(data = null)
            }

            is AddTask -> {
                addTask(event.form)
            }

            is DeleteClicked -> {
                deleteTask(event.taskId)
            }

            is UpdateStatusClicked -> {
                updateTaskStatus(event.taskId, event.status)
            }

            is TaskStatusClicked -> {
                val task = event.task
                val nextStatuses = getAvailableStatusesUseCase(task.status)
                if (nextStatuses.isEmpty()) return

                showTaskStatusModal.value = ModalConfig(
                    data = TaskStatusModalData(task.id, nextStatuses)
                )
            }

            is HideStatusModal -> {
                showTaskStatusModal.value = ModalConfig(data = null)
            }

            is TaskClicked -> {
                output(Output.OpenDetails(event.taskId))
            }

            RetryClicked -> {
                loadTasks()
            }
        }
    }

    private fun loadTasks() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        tasksFlowJob?.cancel()
        tasksFlowJob = taskRepository.getTasksFlow()
            .onEach { tasks ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    tasks = tasks.map { task -> task.toUiModel() }
                )
            }
            .catch { _uiState.value = _uiState.value.copy(isLoading = false, isError = true) }
            .launchIn(coroutineScope)
    }

    private fun addTask(task: NewTask) {
        coroutineScope.launch {
            taskRepository.addTask(task)
        }
    }

    private fun deleteTask(taskId: String) {
        coroutineScope.launch { taskRepository.deleteTask(taskId) }
    }

    private fun updateTaskStatus(taskId: String, status: TaskStatus) {
        coroutineScope.launch { taskRepository.updateTaskStatus(taskId, status) }
    }
}
