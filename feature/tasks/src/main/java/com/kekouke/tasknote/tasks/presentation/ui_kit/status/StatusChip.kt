package com.kekouke.tasknote.tasks.presentation.ui_kit.status

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.conditional(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier
): Modifier = composed {
    if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

@Composable
internal fun StatusChip(
    state: StatusState,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier
            .clip(CircleShape)
            .conditional(onClick != null) {
                clickable { onClick?.invoke() }
            }
            .background(state.color.copy(alpha = 0.2f))
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
        StatusChip(state = StatusState.TODO, onClick = {})
        StatusChip(state = StatusState.InProgress, onClick = {})
        StatusChip(state = StatusState.Done, onClick = {})
    }
}
