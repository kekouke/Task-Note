package com.kekouke.tasknote.tasks.presentation.ui_kit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kekouke.task.R
import com.kekouke.tasknote.theme.Purple80
import com.kekouke.tasknote.theme.Regular13

@Composable
internal fun CreationTime(
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
