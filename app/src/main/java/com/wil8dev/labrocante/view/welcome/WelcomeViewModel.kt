package com.wil8dev.labrocante.view.welcome

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wil8dev.labrocante.view.core.UiModelHandlerFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    uiModelHandlerFactory: UiModelHandlerFactory,
) : ViewModel() {

    private val uiModelHandler = uiModelHandlerFactory.buildSavedStateUiStateHandler(
        savedStateHandle = savedStateHandle,
        defaultUiModel = WelcomeUiModel(),
    )
    val uiModelFlow get() = uiModelHandler.uiModelFlow

    fun navigateToHome() {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(navigation = WelcomeUiModel.Navigation.Home)
            }
        }
    }
}
