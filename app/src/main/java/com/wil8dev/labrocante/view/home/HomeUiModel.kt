package com.wil8dev.labrocante.view.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeUiModel(
    val uiState: UiState = UiState.Content,
    val navigation: Navigation = Navigation.None,
) : Parcelable {

    sealed interface Navigation : Parcelable {

        @Parcelize
        data object None : Navigation

        @Parcelize
        data object Welcome : Navigation

        @Parcelize
        data object Assistant : Navigation
    }

    sealed interface UiState : Parcelable {

        @Parcelize
        data object Content : UiState
    }
}
