package com.wil8dev.labrocante.view.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wil8dev.labrocante.view.core.compose.LaBrocanteButton
import com.wil8dev.labrocante.view.core.compose.LaBrocanteNavigationBarItem
import com.wil8dev.labrocante.view.core.compose.LaBrocantePreview
import com.wil8dev.labrocante.view.core.compose.LaBrocanteScaffold
import com.wil8dev.labrocante.view.core.compose.LaBrocanteSpacer
import com.wil8dev.labrocante.view.core.compose.LaBrocanteText
import com.wil8dev.labrocante.view.core.compose.LaBrocanteTextField

@Composable
fun AccountContent(
    modifier: Modifier = Modifier,
    uiModel: AccountUiModel,
    emailTextFieldValue: TextFieldValue,
    onEmailChange: (value: TextFieldValue) -> Unit = {},
    passwordTextFieldValue: TextFieldValue,
    onPasswordChange: (value: TextFieldValue) -> Unit = {},
    onLoginClicked: () -> Unit = {},
    onHomeClicked: () -> Unit = {},
) {
    LaBrocanteScaffold(
        modifier = modifier,
        selectedNavigationBarItem = LaBrocanteNavigationBarItem.Account,
        onHomeClicked = onHomeClicked,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(it),
            contentAlignment = Alignment.TopCenter,
        ) {
            when (uiModel.uiState) {
                is AccountUiModel.UiState.Content.Disconnected -> {
                    AccountLoadedDisconnectedContent(
                        uiState = uiModel.uiState,
                        emailTextFieldValue = emailTextFieldValue,
                        onEmailChange = onEmailChange,
                        passwordTextFieldValue = passwordTextFieldValue,
                        onPasswordChange = onPasswordChange,
                        onLoginClicked = onLoginClicked,
                    )
                }

                is AccountUiModel.UiState.Content.Connected -> {
                    AccountLoadedConnectedContent(
                        uiState = uiModel.uiState,
                        onLogoutClicked = onLoginClicked,
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountLoadedDisconnectedContent(
    modifier: Modifier = Modifier,
    uiState: AccountUiModel.UiState.Content.Disconnected,
    emailTextFieldValue: TextFieldValue,
    onEmailChange: (value: TextFieldValue) -> Unit,
    passwordTextFieldValue: TextFieldValue,
    onPasswordChange: (value: TextFieldValue) -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LaBrocanteSpacer()
        LaBrocanteText(
            modifier = Modifier.fillMaxWidth(),
            text = "Login",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
        LaBrocanteSpacer()
        LaBrocanteTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailTextFieldValue,
            onValueChange = onEmailChange,
            label = "Email",
        )
        LaBrocanteSpacer()
        LaBrocanteTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordTextFieldValue,
            onValueChange = onPasswordChange,
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardImeAction = ImeAction.Done,
        )
        if (uiState.hasError) {
            LaBrocanteSpacer()
            LaBrocanteText(
                modifier = Modifier.fillMaxWidth(),
                text = "Connection error",
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        }
        LaBrocanteSpacer()
        LaBrocanteButton(
            text = "Login",
            onClick = onLoginClicked,
        )
        LaBrocanteSpacer()
    }
}

@Composable
private fun AccountLoadedConnectedContent(
    modifier: Modifier = Modifier,
    uiState: AccountUiModel.UiState.Content.Connected,
    onLogoutClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LaBrocanteSpacer()
        LaBrocanteText(
            modifier = Modifier.fillMaxWidth(),
            text = "You are logged in as ${uiState.userName}",
            textAlign = TextAlign.Center,
        )
        LaBrocanteSpacer()
        LaBrocanteButton(
            text = "Logout",
            onClick = onLogoutClicked,
        )
    }
}

@LaBrocantePreview
@Composable
private fun Preview() {
    AccountContent(
        uiModel = AccountUiModel(),
        emailTextFieldValue = TextFieldValue(),
        passwordTextFieldValue = TextFieldValue(),
    )
}
