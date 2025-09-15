package com.kekouke.tasknote.login.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.kekouke.tasknote.login.R
import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.login.presentation.LoginComponent.Error
import com.kekouke.tasknote.login.presentation.LoginComponent.Event
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.EmailChanged
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.PasswordChanged
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.SignInClicked
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.SignUpClicked
import com.kekouke.tasknote.login.presentation.LoginComponent.Output
import com.kekouke.tasknote.login.presentation.LoginComponent.UiState
import com.kekouke.tasknote.utils.models.Text
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class LoginComponentImpl(
    componentContext: ComponentContext,
    private val userRepository: UserRepository,
    private val output: (Output) -> Unit,
) : LoginComponent, ComponentContext by componentContext {

    override val uiState: Value<UiState>
        get() = _uiState

    override val errorsFlow: SharedFlow<Error>
        get() = _errorsFlow.asSharedFlow()

    private val _uiState = MutableValue(UiState())
    private val _errorsFlow = MutableSharedFlow<Error>()

    private val coroutineScope = coroutineScope()

    override fun onEvent(event: Event) {
        when (event) {
            is EmailChanged -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            is PasswordChanged -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

            is SignInClicked -> {
                signIn()
            }

            SignUpClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            return
        }
        _uiState.value = state.copy(isSignUpProcessing = true, errorMessage = null)

        coroutineScope.launch {
            userRepository.signUp(
                state.email.trim(),
                state.password.trim()
            ).foldAuthResult()
        }
    }

    private fun signIn() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            return
        }
        _uiState.value = state.copy(isSignInProcessing = true, errorMessage = null)

        coroutineScope.launch {
            userRepository.signIn(
                state.email.trim(),
                state.password.trim()
            ).foldAuthResult()
        }
    }

    private suspend fun Result<Unit>.foldAuthResult() {
        fold(
            onSuccess = { output(Output.LoginCompleted) },
            onFailure = { exception ->
                _uiState.value = _uiState.value.copy(
                    isSignInProcessing = false,
                    isSignUpProcessing = false
                )
                exception.message?.let { msg ->
                    _uiState.value = _uiState.value.copy(errorMessage = Text.Value(msg))
                } ?: run {
                    _errorsFlow.emit(
                        Error(Text.Resource(R.string.label_login_unknown_authorization_error))
                    )
                }
            }
        )
    }
}
