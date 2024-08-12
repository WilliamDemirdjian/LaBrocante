package com.wil8dev.labrocante.view.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WelcomeView(
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    val uiModel by viewModel.uiModelFlow.collectAsStateWithLifecycle()
    LaunchedEffect(uiModel.navigation) {
        when (uiModel.navigation) {
            is WelcomeUiModel.Navigation.None -> {}
            is WelcomeUiModel.Navigation.Home -> navigateToHome()
        }
    }

    WelcomeContent(
        modifier = modifier,
        onClose = viewModel::navigateToHome,
    )
}
