package com.wil8dev.labrocante.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToWelcome: () -> Unit,
    navigateToAssistant: () -> Unit,
    onAccountClicked: () -> Unit,
) {
    val uiModel by viewModel.uiModelFlow.collectAsStateWithLifecycle()
    LaunchedEffect(uiModel.navigation) {
        when (uiModel.navigation) {
            is HomeUiModel.Navigation.Welcome -> navigateToWelcome()
            is HomeUiModel.Navigation.Assistant -> navigateToAssistant()
            is HomeUiModel.Navigation.None -> {}
        }
    }

    HomeContent(
        modifier = modifier,
        onAccountClicked = onAccountClicked,
        onHelpMeFindClick = viewModel::navigateToAssistant,
    )
}
