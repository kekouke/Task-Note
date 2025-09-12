package com.kekouke.tasknote.tasks.presentation.details.models

import com.kekouke.tasknote.tasks.domain.TaskStatus

internal data class TaskDetailsUiModel(
    val title: String,
    val description: String,
    val creationTime: String,
    val status: TaskStatus
) {
    companion object {
        fun preview() = TaskDetailsUiModel(
            title = "Market Research",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce fermentum imperdiet accumsan. Cras viverra bibendum velit, nec dapibus ipsum condimentum finibus. Aenean augue neque, ornare in tincidunt at, feugiat non nibh. Praesent laoreet dapibus elementum. Curabitur ac tempor sem. Fusce tempus porta nisl eget aliquet. Maecenas luctus lorem non accumsan fringilla. Nunc venenatis viverra mauris, vel tempor arcu auctor quis",
            creationTime = "11 сент. 2025 20:24",
            status = TaskStatus.Done
        )
    }
}
