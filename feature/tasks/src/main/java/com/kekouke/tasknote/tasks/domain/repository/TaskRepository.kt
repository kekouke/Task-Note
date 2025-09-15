package com.kekouke.tasknote.tasks.domain.repository

import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.domain.entities.Task
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TaskRepository {
    fun getTasksFlow(): Flow<List<Task>>
    suspend fun getTask(id: String): Result<Task>
    suspend fun addTask(task: NewTask): Result<Unit>
    suspend fun deleteTask(taskId: String): Result<Unit>
    suspend fun updateTaskStatus(taskId: String, status: TaskStatus): Result<Unit>
}
