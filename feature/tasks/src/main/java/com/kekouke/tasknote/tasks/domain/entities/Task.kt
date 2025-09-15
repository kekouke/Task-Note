package com.kekouke.tasknote.tasks.domain.entities

import com.kekouke.tasknote.tasks.domain.TaskStatus
import org.joda.time.LocalDateTime

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val utcCreationTime: LocalDateTime,
    val status: TaskStatus
)
