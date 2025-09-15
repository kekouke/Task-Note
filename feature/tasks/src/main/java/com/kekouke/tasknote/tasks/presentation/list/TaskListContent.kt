package com.kekouke.tasknote.tasks.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.DeleteClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.NewTaskClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.RetryClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.TaskClicked
import com.kekouke.tasknote.tasks.presentation.list.TaskListComponent.Event.TaskStatusClicked
import com.kekouke.tasknote.tasks.presentation.list.models.TaskUiModel
import com.kekouke.tasknote.tasks.presentation.list.ui_kit.AddTaskModal
import com.kekouke.tasknote.tasks.presentation.list.ui_kit.SelectTaskStatusModal
import com.kekouke.tasknote.tasks.presentation.list.ui_kit.TaskCard
import com.kekouke.tasknote.tasks.presentation.ui_kit.ErrorState
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH1
import com.kekouke.tasknote.theme.TitleH2
import com.kekouke.tasknote.theme.labelBlack
import com.kekouke.tasknote.theme.labelWhite

@Composable
internal fun TaskListContent(
    component: TaskListComponent,
    modifier: Modifier = Modifier,
) {
    val uiState by component.uiState.subscribeAsState()

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(stringResource(R.string.title_task_list)) },
        floatingActionButton = {
            AnimatedVisibility(visible = uiState.isLoading.not() && uiState.isError.not()) {
                FloatingActionButton(
                    onClick = { component.onEvent(NewTaskClicked) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.label_add_task)
                    )
                }
            }
        }
    ) { paddings ->
        Box(Modifier.fillMaxSize()) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                uiState.isError -> {
                    ErrorState(
                        modifier = Modifier.align(Alignment.Center),
                        onRetry = { component.onEvent(RetryClicked) }
                    )
                }

                uiState.tasks.isEmpty() -> {
                    EmptyState(Modifier.align(Alignment.Center))
                }

                else -> Content(
                    modifier = Modifier.padding(top = paddings.calculateTopPadding()),
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        bottom = 100.dp + paddings.calculateBottomPadding(),
                        start = 8.dp,
                        end = 8.dp
                    ),
                    tasks = uiState.tasks,
                    component = component
                )
            }
        }
    }

    val showStatusesModal by component.showTaskStatusModal.collectAsState()
    showStatusesModal.data?.let { data ->
        SelectTaskStatusModal(
            availableStatuses = data.nextStatuses,
            onDismissRequest = { component.onEvent(Event.HideStatusModal) },
            onSelectStatus = { status ->
                component.onEvent(Event.UpdateStatusClicked(data.taskId, status))
                component.onEvent(Event.HideStatusModal)
            }
        )
    }

    val showNewTaskModal by component.showNewTaskModal.collectAsState()
    showNewTaskModal.data?.let { _ ->
        AddTaskModal(
            onDismissRequest = { component.onEvent(Event.HideAddTaskModal) },
            onSubmit = {
                component.onEvent(Event.AddTask(it))
                component.onEvent(Event.HideAddTaskModal)
            }
        )
    }
}

@Composable
private fun Content(
    tasks: List<TaskUiModel>,
    component: TaskListComponent,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tasks, key = { it.id }) { task ->
            TaskCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
                task = task,
                onClick = { component.onEvent(TaskClicked(task.id)) },
                onDeleteClick = { component.onEvent(DeleteClicked(task.id)) },
                onStatusClick = { component.onEvent(TaskStatusClicked(task)) }
            )
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.label_task_list_empty_state),
        style = TitleH2,
        color = labelBlack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(title: String, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = labelWhite
        ),
        title = {
            Text(
                text = title,
                color = labelBlack,
                style = TitleH1
            )
        }
    )
}

@Preview
@Composable
private fun TaskListContentRoot() {
    TaskNoteTheme {
        TaskListContent(
            modifier = Modifier.fillMaxSize(),
            component = PreviewTaskListComponentImpl()
        )
    }
}
