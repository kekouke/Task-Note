package com.kekouke.tasknote.login.presentation

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.login.presentation.LoginComponent.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

internal class PreviewLoginComponent : LoginComponent {

    override val uiState: Value<UiState>
        get() = MutableValue(
            UiState(
                email = "test@gmail.com",
            )
        )

    override val errorsFlow: SharedFlow<LoginComponent.Error>
        get() = MutableSharedFlow()

    override fun onEvent(event: LoginComponent.Event) = Unit
}