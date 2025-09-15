package com.kekouke.tasknote.root.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.subscribeAsState()

    Children(
        modifier = modifier,
        stack = stack
    ) { child ->
        when (val current = child.instance) {
            is RootComponent.Child.Login -> {

            }

            is RootComponent.Child.Tasks -> {

            }
        }
    }
}
