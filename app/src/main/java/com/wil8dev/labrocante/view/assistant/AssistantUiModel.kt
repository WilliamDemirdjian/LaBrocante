package com.wil8dev.labrocante.view.assistant

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssistantUiModel(
    val uiState: UiState = UiState.Content,
    val navigation: Navigation = Navigation.None,
) : Parcelable {

    sealed interface Navigation : Parcelable {

        @Parcelize
        data object None : Navigation

        @Parcelize
        data class Prompt(
            val isSelling: Boolean,
        ) : Navigation
    }

    sealed interface UiState : Parcelable {

        @Parcelize
        data object Content : UiState
    }
}
