package com.kekouke.tasknote.tasks.data

import com.kekouke.tasknote.tasks.data.datasource.TaskDataSource
import com.kekouke.tasknote.tasks.domain.entities.Task
import com.kekouke.tasknote.tasks.domain.repository.TaskRepository
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import com.kekouke.tasknote.utils.models.runCatchingNonCancellation
import javax.inject.Inject

class TaskDataRepository @Inject constructor(
    private val taskDataSource: TaskDataSource
) : TaskRepository {

    override fun getTasksFlow() = taskDataSource.getTasksFlow()

    override suspend fun getTask(id: String): Result<Task> {
        return runCatchingNonCancellation { taskDataSource.getTask(id) }
    }

    override suspend fun addTask(task: NewTask): Result<Unit> = runCatchingNonCancellation {
        taskDataSource.addTask(task)
    }

    override suspend fun deleteTask(taskId: String): Result<Unit> = runCatchingNonCancellation {
        taskDataSource.removeTask(taskId)
    }

    override suspend fun updateTaskStatus(
        taskId: String,
        status: com.kekouke.tasknote.tasks.domain.TaskStatus
    ): Result<Unit> = runCatchingNonCancellation {
        taskDataSource.moveStatusTo(taskId, status)
    }
}
