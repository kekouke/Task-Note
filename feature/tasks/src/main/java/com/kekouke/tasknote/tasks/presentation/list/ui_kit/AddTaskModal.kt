package com.kekouke.tasknote.tasks.presentation.list.ui_kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kekouke.task.R
import com.kekouke.tasknote.tasks.presentation.list.models.NewTask
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.TitleH2
import com.kekouke.tasknote.theme.labelBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddTaskModal(
    onDismissRequest: () -> Unit,
    onSubmit: (NewTask) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        AddTaskModalContent(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            onSubmit = onSubmit,
            onDismiss = onDismissRequest
        )
    }
}

@Composable
private fun AddTaskModalContent(
    modifier: Modifier = Modifier,
    onSubmit: (NewTask) -> Unit,
    onDismiss: () -> Unit
) {
    var form by rememberSaveable { mutableStateOf(NewTask()) }
    
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.label_add_task),
            textAlign = TextAlign.Center,
            style = TitleH2,
            color = labelBlack
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = form.title,
            onValueChange = { form = form.copy(title = it) },
            label = { Text(stringResource(R.string.label_task_title)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = form.description,
            onValueChange = { form = form.copy(description = it) },
            label = { Text(stringResource(R.string.label_task_description)) },
            minLines = 3,
            maxLines = 5,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.label_cancel))
            }
            
            Button(
                modifier = Modifier.weight(1f),
                onClick = { onSubmit(form) },
                enabled = form.isValid
            ) {
                Text(stringResource(R.string.label_add))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddTaskModalContentPreview() {
    TaskNoteTheme {
        AddTaskModalContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onSubmit = {},
            onDismiss = {}
        )
    }
}
