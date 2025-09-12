package com.kekouke.tasknote.tasks.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.presentation.list.models.TaskUiModel
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH1
import com.kekouke.tasknote.theme.labelBlack
import com.kekouke.tasknote.theme.labelWhite

@Composable
fun TaskListContent(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(stringResource(R.string.title_task_list)) }
    ) { paddings ->
        LazyColumn(
            modifier = Modifier.padding(top = paddings.calculateTopPadding()),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = paddings.calculateBottomPadding(),
                start = 8.dp,
                end = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(25) {
                TaskCard(
                    modifier = Modifier.fillMaxWidth(),
                    task = TaskUiModel.preview()
                )
            }
        }
    }
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
        TaskListContent(Modifier.fillMaxSize())
    }
}
