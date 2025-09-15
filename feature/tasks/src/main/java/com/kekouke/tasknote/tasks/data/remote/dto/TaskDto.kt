package com.kekouke.tasknote.tasks.data.remote.dto

import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.domain.entities.Task
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class TaskDto(
    var title: String = "",
    var description: String = "",
    var utcCreationTime: Long = 0L,
    var status: String = ""
)

fun TaskDto.toDomain(id: String) = Task(
    id = id,
    title = title,
    description = description,
    utcCreationTime = DateTime(utcCreationTime, DateTimeZone.UTC).toLocalDateTime(),
    status = TaskStatus.valueOf(status)
)
