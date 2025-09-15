package com.kekouke.tasknote.tasks.presentation.list.models

import java.io.Serializable

data class NewTask(
    val title: String = "",
    val description: String = "",
) : Serializable {
    val isValid: Boolean
        get() = title.isNotBlank()
}
