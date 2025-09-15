package com.kekouke.tasknote.tasks.presentation.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.kekouke.tasknote.tasks.domain.repository.TaskRepository
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent.Event
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent.Event.BackClicked
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent.Output
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent.UiState
import com.kekouke.tasknote.tasks.presentation.details.models.toUiModel
import kotlinx.coroutines.launch

internal class TaskDetailsComponentImpl(
    componentContext: ComponentContext,
    private val taskId: String,
    private val taskRepository: TaskRepository,
    private val output: (Output) -> Unit
) : TaskDetailsComponent, ComponentContext by componentContext {

    override val uiState: Value<UiState>
        get() = _uiState

    private val _uiState: MutableValue<UiState> = MutableValue(UiState.Loading)

    private val coroutineScope = coroutineScope()

    init {
        loadTaskDetails()
    }

    override fun onEvent(event: Event) {
        when (event) {
            BackClicked -> output(Output.Exit)
            Event.RetryClicked -> retry()
        }
    }

    private fun retry() {
        if (_uiState.value == UiState.Loading) return
        loadTaskDetails()
    }

    private fun loadTaskDetails() {
        _uiState.value = UiState.Loading
        coroutineScope.launch {
            taskRepository.getTask(taskId)
                .onSuccess { _uiState.value = UiState.Content(it.toUiModel) }
                .onFailure { _uiState.value = UiState.Error }
        }
    }
}
