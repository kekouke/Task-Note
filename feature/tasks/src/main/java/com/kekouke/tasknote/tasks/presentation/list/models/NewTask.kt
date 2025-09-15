package com.kekouke.tasknote.tasks.presentation.list.models

data class NewTask(
    val title: String = "",
    val description: String = "",
) {
    val isValid: Boolean
        get() = title.isNotBlank()
}
