package com.kekouke.tasknote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.retainedComponent
import com.kekouke.tasknote.root.di.dependencies.RootDependencies
import com.kekouke.tasknote.root.presentation.RootComponentImpl
import com.kekouke.tasknote.root.presentation.RootContent
import com.kekouke.tasknote.theme.TaskNoteTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootDependencies: RootDependencies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = retainedComponent { context ->
            RootComponentImpl(
                componentContext = context,
                dependencies = rootDependencies
            )
        }

        enableEdgeToEdge()
        setContent {
            TaskNoteTheme {
                RootContent(
                    component = rootComponent,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
