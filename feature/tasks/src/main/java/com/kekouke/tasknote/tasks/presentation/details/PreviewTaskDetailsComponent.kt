package com.kekouke.tasknote.tasks.presentation.details

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsComponent.UiState
import com.kekouke.tasknote.tasks.presentation.details.models.TaskDetailsUiModel

internal class PreviewTaskDetailsComponent : TaskDetailsComponent {

    override val uiState: Value<UiState>
        get() = MutableValue(
            UiState.Content(TaskDetailsUiModel.preview())
        )

    override fun onEvent(event: TaskDetailsComponent.Event) = Unit
}