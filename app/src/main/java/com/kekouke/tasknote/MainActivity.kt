package com.kekouke.tasknote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.kekouke.tasknote.tasks.presentation.list.TaskListContent
import com.kekouke.tasknote.theme.TaskNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskNoteTheme {
                TaskListContent(Modifier.fillMaxSize())
            }
        }
    }
}
