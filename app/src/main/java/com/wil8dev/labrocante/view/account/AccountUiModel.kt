package com.wil8dev.labrocante.view.account

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountUiModel(
    val uiState: UiState = UiState.Content.Disconnected(),
    val navigation: Navigation = Navigation.None,
) : Parcelable {

    sealed interface Navigation : Parcelable {

        @Parcelize
        data object None : Navigation
    }

    sealed interface UiState : Parcelable {

        sealed interface Content : UiState {
            @Parcelize
            data class Connected(
                val userName: String,
            ) : Content

            @Parcelize
            data class Disconnected(
                val hasError: Boolean = false,
            ) : Content
        }
    }
}
