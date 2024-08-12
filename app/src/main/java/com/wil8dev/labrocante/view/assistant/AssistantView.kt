package com.wil8dev.labrocante.view.assistant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AssistantView(
    modifier: Modifier = Modifier,
    viewModel: AssistantViewModel = hiltViewModel(),
    navigateToPrompt: (isSelling: Boolean) -> Unit,
) {
    val uiModel by viewModel.uiModelFlow.collectAsStateWithLifecycle()
    LaunchedEffect(uiModel.navigation) {
        when (val navigation = uiModel.navigation) {
            is AssistantUiModel.Navigation.None -> {}
            is AssistantUiModel.Navigation.Prompt -> navigateToPrompt(navigation.isSelling)
        }
    }

    AssistantContent(
        modifier = modifier,
        onSellClicked = { viewModel.navigateToPrompt(isSelling = true) },
        onSearchClicked = { viewModel.navigateToPrompt(isSelling = false) },
    )
}
