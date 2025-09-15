package com.kekouke.tasknote.login.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.kekouke.tasknote.login.R
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.EmailChanged
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.PasswordChanged
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.SignInClicked
import com.kekouke.tasknote.login.presentation.LoginComponent.Event.SignUpClicked
import com.kekouke.tasknote.theme.TaskNoteTheme
import com.kekouke.tasknote.theme.bgPrimary
import com.kekouke.tasknote.utils.models.asString
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginContent(
    component: LoginComponent,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState by component.uiState.subscribeAsState()

    LaunchedEffect(Unit) {
        component.errorsFlow
            .onEach { error ->
                Toast.makeText(
                    context,
                    error.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }.launchIn(this)
    }

    Column(
        modifier = modifier
            .background(bgPrimary)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.headlineMedium,
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            enabled = uiState.areInputEnabled,
            value = uiState.email,
            onValueChange = { component.onEvent(EmailChanged(it)) },
            label = { Text(stringResource(R.string.login_email_hint)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = uiState.areInputEnabled,
            value = uiState.password,
            onValueChange = { component.onEvent(PasswordChanged(it)) },
            label = { Text(stringResource(R.string.login_password_hint)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
        )

        uiState.errorMessage?.let { error ->
            Text(
                text = error.asString(LocalContext.current),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        LoginButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.canSendAuthForm,
            isLoading = uiState.isSignInProcessing,
            text = stringResource(R.string.action_sign_in),
            onClick = { component.onEvent(SignInClicked) }
        )

        LoginButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.canSendAuthForm,
            isLoading = uiState.isSignUpProcessing,
            text = stringResource(R.string.action_sign_up),
            onClick = { component.onEvent(SignUpClicked) }
        )
    }
}

@Composable
private fun LoginButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text(text)
        }
    }
}


@Preview
@Composable
private fun LoginContentPreview() {
    TaskNoteTheme {
        LoginContent(
            component = PreviewLoginComponent(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
