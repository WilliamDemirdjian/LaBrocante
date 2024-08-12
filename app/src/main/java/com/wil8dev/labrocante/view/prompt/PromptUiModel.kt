package com.wil8dev.labrocante.view.prompt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PromptUiModel(
    val uiState: UiState = UiState.Content(),
    val navigation: Navigation = Navigation.None,
) : Parcelable {

    sealed interface Navigation : Parcelable {

        @Parcelize
        data object None : Navigation
    }

    sealed interface UiState : Parcelable {

        @Parcelize
        data class Content(
            val isSelling: Boolean = true,
            val announcement: Announcement? = null,
            val suggestedAnnouncement: SuggestedAnnouncement? = null,
        ) : UiState, Parcelable {

            @Parcelize
            data class Announcement(
                val title: String? = null,
                val description: String? = null,
                val category: String? = null,
                val price: String? = null,
                val savedCarbonFootprint: String? = null,
            ) : Parcelable

            @Parcelize
            data class SuggestedAnnouncement(
                val description: String? = null,
                val announcements: List<Announcement> = emptyList(), // TODO when mapped to Firestore
            ) : Parcelable
        }
    }
}
