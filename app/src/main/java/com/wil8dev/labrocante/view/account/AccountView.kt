package com.wil8dev.labrocante.view.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AccountView(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
    onHomeClicked: () -> Unit,
) {
    val uiModel by viewModel.uiModelFlow.collectAsStateWithLifecycle()

    LaunchedEffect(uiModel.navigation) {
        when (uiModel.navigation) {
            is AccountUiModel.Navigation.None -> {}
        }
    }

    AccountContent(
        modifier = modifier,
        uiModel = uiModel,
        emailTextFieldValue = viewModel.emailTextFieldValue,
        onEmailChange = viewModel::updateEmail,
        passwordTextFieldValue = viewModel.passwordTextFieldValue,
        onPasswordChange = viewModel::updatePassword,
        onLoginClicked = viewModel::login,
        onHomeClicked = onHomeClicked,
    )
}
