package com.kekouke.tasknote.tasks.presentation.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.domain.TaskStatus
import com.kekouke.tasknote.tasks.presentation.ui_kit.status.StatusChip
import com.kekouke.tasknote.tasks.presentation.ui_kit.status.toUiState
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH2
import com.kekouke.tasknote.theme.labelBlack
import com.kekouke.tasknote.theme.labelWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SelectTaskStatusModal(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        SelectTaskStatusModalContent(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            listOf(
                TaskStatus.Todo,
            )
        )
    }
}

@Composable
private fun SelectTaskStatusModalContent(
    modifier: Modifier = Modifier,
    statuses: List<TaskStatus>
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.label_task_select_status),
            textAlign = TextAlign.Center,
            style = TitleH2,
            color = labelBlack
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(labelWhite)
        ) {
            statuses.forEachIndexed { index, status ->
                StatusItem(
                    modifier = Modifier.fillMaxWidth(),
                    status = status,
                    onClick = {}
                )
                if (index < statuses.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun StatusItem(
    status: TaskStatus,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        StatusChip(status.toUiState())
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectTaskStatusModalPreview() {
    TaskNoteTheme {
        SelectTaskStatusModalContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            statuses = listOf(
                TaskStatus.Todo,
                TaskStatus.InProgress,
                TaskStatus.Done
            )
        )
    }
}
