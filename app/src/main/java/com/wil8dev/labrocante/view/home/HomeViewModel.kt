package com.wil8dev.labrocante.view.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wil8dev.labrocante.PreferencesManager
import com.wil8dev.labrocante.view.core.UiModelHandlerFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    uiModelHandlerFactory: UiModelHandlerFactory,
    preferencesManager: PreferencesManager,
) : ViewModel() {

    private val uiModelHandler = uiModelHandlerFactory.buildSavedStateUiStateHandler(
        savedStateHandle = savedStateHandle,
        defaultUiModel = HomeUiModel(),
    )
    val uiModelFlow get() = uiModelHandler.uiModelFlow

    init {
        if (preferencesManager.isFirstLaunch) {
            navigateToWelcomeScreen()
            preferencesManager.isFirstLaunch = false
        }
    }

    private fun navigateToWelcomeScreen() {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(navigation = HomeUiModel.Navigation.Welcome)
            }
        }
    }

    fun navigateToAssistant() {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(navigation = HomeUiModel.Navigation.Assistant)
            }
        }
    }
}
