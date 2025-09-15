package com.kekouke.tasknote.root.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kekouke.tasknote.login.presentation.LoginContent

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
                LoginContent(
                    component = current.component,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is RootComponent.Child.Tasks -> {

            }
        }
    }
}
