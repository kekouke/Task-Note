package com.kekouke.tasknote.tasks.presentation.root

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kekouke.tasknote.tasks.presentation.details.TaskDetailsContent
import com.kekouke.tasknote.tasks.presentation.list.TaskListContent
import com.kekouke.tasknote.tasks.presentation.root.TaskRootComponent.Child
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH1
import com.kekouke.tasknote.theme.labelBlack
import com.kekouke.tasknote.theme.labelWhite

@Composable
fun TaskRootContent(
    component: TaskRootComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.subscribeAsState()

    Children(
        modifier = modifier,
        stack = stack
    ) { child ->
        when (val current = child.instance) {
            is Child.Details -> {
                TaskDetailsContent(
                    component = current.component,
                    modifier = modifier
                )
            }

            is Child.List -> {
                TaskListContent(
                    component = current.component,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview
@Composable
private fun TasksContentPreview() {
    TaskNoteTheme {
        // TasksContent(Modifier.fillMaxSize())
    }
}
