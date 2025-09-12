package com.kekouke.tasknote.tasks.presentation.ui_kit.status

import androidx.compose.ui.graphics.Color
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.theme.colorBlue
import com.kekouke.tasknote.theme.colorGreen
import com.kekouke.tasknote.theme.colorOrange

internal sealed class StatusState(
    val textResId: Int,
    val color: Color
) {
    data object Done : StatusState(
        R.string.label_task_status_done,
        colorGreen
    )

    data object InProgress : StatusState(
        R.string.label_task_status_in_progress,
        colorOrange
    )

    data object TODO : StatusState(
        R.string.label_task_status_to_do,
        colorBlue
    )
}

internal fun TaskStatus.toUiState() = when (this) {
    TaskStatus.Todo -> StatusState.TODO
    TaskStatus.InProgress -> StatusState.InProgress
    TaskStatus.Done -> StatusState.Done
}
