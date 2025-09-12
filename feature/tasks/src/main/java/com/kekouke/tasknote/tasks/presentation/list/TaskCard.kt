package com.kekouke.tasknote.tasks.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.presentation.list.models.TaskUiModel
import com.kekouke.tasknote.tasks.presentation.ui_kit.status.StatusChip
import com.kekouke.tasknote.tasks.presentation.ui_kit.status.toUiState
import com.kekouke.tasknote.theme.Purple80
import com.kekouke.tasknote.theme.Regular13
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH2
import com.kekouke.tasknote.theme.labelBlack
import com.kekouke.tasknote.theme.labelSecondary
import com.kekouke.tasknote.theme.labelWhite

@Composable
internal fun TaskCard(
    task: TaskUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            contentColor = labelBlack,
            containerColor = labelWhite
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 8.dp),
                    text = task.title,
                    style = TitleH2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (task.isDeletingAvailable) {
                    Icon(
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = Icons.Filled.Close,
                        tint = labelSecondary,
                        contentDescription = stringResource(R.string.content_description_close)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CreationTime(task.creationTime)
                StatusChip(task.status.toUiState())
            }
        }
    }
}

@Composable
private fun CreationTime(
    time: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_clock),
            tint = Purple80,
            contentDescription = null,
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = time,
            style = Regular13,
            color = Purple80
        )
    }
}

@Preview
@Composable
private fun TaskCardPreview() {
    TaskNoteTheme {
        TaskCard(
            modifier = Modifier.fillMaxWidth(),
            task = TaskUiModel.preview(),
        )
    }
}
