package com.kekouke.tasknote.tasks.presentation.list.models

import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.domain.entities.Task
import com.kekouke.tasknote.tasks.domain.usecase.GetAvailableStatusesUseCase

data class TaskUiModel(
    val id: String,
    val title: String,
    val creationTime: String,
    val status: TaskStatus,
    val isDeletingAvailable: Boolean,
) {
    companion object {
        fun preview() = TaskUiModel(
            id = "preview_id",
            title = "Market Research",
            creationTime = "11 сент. 2025 20:24",
            status = TaskStatus.Done,
            isDeletingAvailable = true,
        )
    }
}

internal fun Task.toUiModel(): TaskUiModel {
    return TaskUiModel(
        id = id,
        title = title,
        creationTime = utcCreationTime.toString("dd MMM YYYY HH:MM"),
        status = status,
        isDeletingAvailable = status == TaskStatus.Todo,
    )
}
