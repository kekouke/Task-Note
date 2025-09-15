package com.kekouke.tasknote.tasks.domain.usecase

import com.kekouke.tasknote.tasks.domain.TaskStatus
import javax.inject.Inject

internal class GetAvailableStatusesUseCase @Inject constructor() {
    
    operator fun invoke(currentStatus: TaskStatus): List<TaskStatus> {
        return when (currentStatus) {
            TaskStatus.Todo -> listOf(TaskStatus.InProgress)
            TaskStatus.InProgress -> listOf(TaskStatus.Done)
            TaskStatus.Done -> emptyList()
        }
    }
}
