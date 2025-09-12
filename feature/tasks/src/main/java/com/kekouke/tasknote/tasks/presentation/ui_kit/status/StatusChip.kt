package com.kekouke.tasknote.tasks.presentation.ui_kit.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun StatusChip(
    state: StatusState,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .clip(CircleShape)
            .background(state.color.copy(alpha = 0.1f))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        text = stringResource(state.textResId),
        color = state.color
    )
}

@Preview
@Composable
private fun TaskStatus() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        StatusChip(StatusState.TODO)
        StatusChip(StatusState.InProgress)
        StatusChip(StatusState.Done)
    }
}
