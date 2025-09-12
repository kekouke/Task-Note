package com.kekouke.tasknote.tasks.presentation.list.models

import com.kekouke.tasknote.tasks.domain.TaskStatus

internal data class TaskUiModel(
    val title: String,
    val creationTime: String,
    val status: TaskStatus,
    val isDeletingAvailable: Boolean
) {
    companion object {
        fun preview() = TaskUiModel(
            title = "Market Research",
            creationTime = "11 сент. 2025 20:24",
            status = TaskStatus.Done,
            isDeletingAvailable = true
        )
    }
}
