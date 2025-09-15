package com.kekouke.tasknote.tasks.presentation.list.models

import java.io.Serializable

data class NewTask(
    val title: String = "",
    val description: String = "",
) : Serializable {
    val hasValidTitle: Boolean
        get() = title.trim().isNotBlank()
}
