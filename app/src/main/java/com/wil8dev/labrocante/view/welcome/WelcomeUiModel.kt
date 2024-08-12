package com.wil8dev.labrocante.view.welcome

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WelcomeUiModel(
    val uiState: UiState = UiState.Content,
    val navigation: Navigation = Navigation.None,
) : Parcelable {

    sealed interface Navigation : Parcelable {

        @Parcelize
        data object None : Navigation

        @Parcelize
        data object Home : Navigation
    }

    sealed interface UiState : Parcelable {

        @Parcelize
        data object Content : UiState
    }
}
