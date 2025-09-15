package com.kekouke.tasknote.tasks.data.remote

import android.content.res.Resources.NotFoundException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.kekouke.tasknote.tasks.data.datasource.TaskDataSource
import com.kekouke.tasknote.tasks.data.remote.dto.TaskDto
import com.kekouke.tasknote.tasks.data.remote.dto.toDomain
import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.domain.entities.Task
import com.kekouke.tasknote.tasks.domain.errors.NotAuthenticated
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime
import javax.inject.Inject

class TaskNetworkDataSource @Inject constructor() : TaskDataSource {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val tasksReference: DatabaseReference
        get() {
            val uid = firebaseAuth.uid ?: throw NotAuthenticated()
            return Firebase.database.getReference("Users/$uid/tasks")
        }

    override fun getTasksFlow(): Flow<List<Task>> = callbackFlow {
        val listener = object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        tasksReference
            .orderByChild("utcCreationTime")
            .addValueEventListener(listener)

        awaitClose { tasksReference.removeEventListener(listener) }
    }.map { snapshot ->
        snapshot.children.mapNotNull { child ->
            val id = child.key ?: return@mapNotNull null
            child.getValue(TaskDto::class.java)?.toDomain(id)
        }.reversed()
    }.flowOn(Dispatchers.IO)

    override suspend fun getTask(id: String): Task {
        val dto = tasksReference.child(id).get().await()
            .getValue(TaskDto::class.java) ?: throw NotFoundException("Not found task with id=$id")

        return dto.toDomain(id)
    }

    override suspend fun addTask(task: NewTask) {
        val dto = TaskDto(
            title = task.title,
            description = task.description,
            utcCreationTime = LocalDateTime.now().toDateTime(DateTimeZone.UTC).millis,
            status = TaskStatus.Todo.name
        )
        tasksReference.push().setValue(dto).await()
    }

    override suspend fun removeTask(taskId: String) {
        tasksReference.child(taskId).removeValue().await()
    }

    override suspend fun moveStatusTo(taskId: String, taskStatus: TaskStatus) {
        tasksReference.child(taskId).child("status").setValue(taskStatus.name).await()
    }
}
