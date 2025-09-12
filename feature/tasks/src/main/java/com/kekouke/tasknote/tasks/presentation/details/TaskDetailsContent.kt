package com.kekouke.tasknote.tasks.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.presentation.details.models.TaskDetailsUiModel
import com.kekouke.tasknote.tasks.presentation.ui_kit.CreationTime
import com.kekouke.tasknote.tasks.presentation.ui_kit.status.StatusChip
import com.kekouke.tasknote.tasks.presentation.ui_kit.status.toUiState
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskDetailsContent(
    task: TaskDetailsUiModel,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back)
                    )
                },
            )
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CreationTime(
                modifier = Modifier.padding(top = 16.dp),
                time = task.creationTime
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = task.title,
                style = TitleH1
            )
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.label_task_details_status))
                StatusChip(task.status.toUiState())
            }

            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Text(task.description)
        }
    }
}

@Preview
@Composable
private fun TaskDetailsContentPreview() {
    TaskNoteTheme {
        TaskDetailsContent(
            modifier = Modifier.fillMaxSize(),
            task = TaskDetailsUiModel.preview()
        )
    }
}
