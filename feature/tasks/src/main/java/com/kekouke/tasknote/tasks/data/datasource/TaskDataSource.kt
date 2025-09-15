package com.kekouke.tasknote.tasks.data.datasource

import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.domain.entities.Task
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
    fun getTasksFlow(): Flow<List<Task>>
    suspend fun getTask(id: String): Task
    suspend fun addTask(task: NewTask)
    suspend fun removeTask(taskId: String)
    suspend fun moveStatusTo(taskId: String, taskStatus: TaskStatus)
}
