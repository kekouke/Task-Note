package com.kekouke.tasknote.tasks.presentation.details

import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.tasks.presentation.details.models.TaskDetailsUiModel

interface TaskDetailsComponent {

    val uiState: Value<UiState>

    fun onEvent(event: Event)

    sealed class UiState {
        data object Loading : UiState()
        data object Error : UiState()
        data class Content(val taskDetailsUiModel: TaskDetailsUiModel) : UiState()
    }

    sealed interface Event {
        data object BackClicked : Event
        data object RetryClicked : Event
    }

    sealed interface Output {
        data object Exit : Output
    }
}
