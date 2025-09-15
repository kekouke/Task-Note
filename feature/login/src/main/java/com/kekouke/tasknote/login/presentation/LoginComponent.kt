package com.kekouke.tasknote.login.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.kekouke.tasknote.utils.models.Text
import kotlinx.coroutines.flow.SharedFlow

interface LoginComponent {

    val uiState: Value<UiState>
    val errorsFlow: SharedFlow<Error>

    fun onEvent(event: Event)

    data class UiState(
        val email: String = "",
        val password: String = "",
        val isSignInProcessing: Boolean = false,
        val isSignUpProcessing: Boolean = false,
        val errorMessage: Text? = null
    ) {
        val areInputEnabled = (isSignInProcessing || isSignUpProcessing).not()
        val canSendAuthForm =
            email.trim().isNotBlank() && password.trim().isNotBlank() && areInputEnabled
    }

    sealed class Event {
        data class EmailChanged(val email: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        data object SignInClicked : Event()
        data object SignUpClicked : Event()
    }

    data class Error(val message: Text)

    sealed class Output {
        data object LoginCompleted : Output()
    }

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            output: (Output) -> Unit
        ): LoginComponent
    }
}
