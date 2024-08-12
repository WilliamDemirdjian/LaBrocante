package com.wil8dev.labrocante.view.prompt

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.wil8dev.labrocante.view.core.compose.LaBrocantePreview
import com.wil8dev.labrocante.view.core.compose.LaBrocanteScaffold
import com.wil8dev.labrocante.view.core.compose.theme.LaBrocanteTheme

@Composable
fun PromptContent(
    modifier: Modifier = Modifier,
    uiModel: PromptUiModel,
    onOptionSelected: (Boolean) -> Unit = {},
    onShowResults: () -> Unit = {},
    onMediaSelected: (Intent) -> Unit = {},
    onTakePhoto: () -> Unit = {},
    mediaUris: List<Uri>,
    onRemoveMedia: (Uri) -> Unit = {},
    userDescriptionTextFieldValue: TextFieldValue = TextFieldValue(""),
    onUserDescriptionChange: (value: TextFieldValue) -> Unit = {},
    titleTextFieldValue: TextFieldValue = TextFieldValue(""),
    onTitleChange: (value: TextFieldValue) -> Unit = {},
    descriptionTextFieldValue: TextFieldValue = TextFieldValue(""),
    onDescriptionChange: (value: TextFieldValue) -> Unit = {},
    categoryTextFieldValue: TextFieldValue = TextFieldValue(""),
    onCategoryChange: (value: TextFieldValue) -> Unit = {},
    priceTextFieldValue: TextFieldValue = TextFieldValue(""),
    onPriceChange: (value: TextFieldValue) -> Unit = {},
    savedCarbonFootprintTextFieldValue: TextFieldValue = TextFieldValue(""),
    onSavedCarbonFootprintChange: (value: TextFieldValue) -> Unit = {},
    onPublishClicked: () -> Unit = {},
) {
    val fadeAnimationSpec = tween<Float>(durationMillis = 500)
    var showModalBottomSheet by remember { mutableStateOf(false) }
    LaBrocanteScaffold(
        modifier = modifier
            .fillMaxSize(),
        topBarText = "Try the assistant",
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            targetState = uiModel.uiState,
            transitionSpec = {
                fadeIn(fadeAnimationSpec) togetherWith fadeOut(fadeAnimationSpec)
            },
            contentAlignment = Alignment.Center,
            label = "mainContent",
        ) { uiState ->
            when (uiState) {
                is PromptUiModel.UiState.Content -> PromptLoadedContent(
                    uiState = uiState,
                    onOptionSelected = onOptionSelected,
                    mediaUris = mediaUris,
                    onShowModalBottomSheet = { showModalBottomSheet = true }, // TODO use VM
                    onShowResults = onShowResults,
                    onRemoveMedia = onRemoveMedia,
                    userDescriptionTextFieldValue = userDescriptionTextFieldValue,
                    onUserDescriptionChange = onUserDescriptionChange,
                    titleTextFieldValue = titleTextFieldValue,
                    onTitleChange = onTitleChange,
                    descriptionTextFieldValue = descriptionTextFieldValue,
                    onDescriptionChange = onDescriptionChange,
                    categoryTextFieldValue = categoryTextFieldValue,
                    onCategoryChange = onCategoryChange,
                    priceTextFieldValue = priceTextFieldValue,
                    onPriceChange = onPriceChange,
                    savedCarbonFootprintTextFieldValue = savedCarbonFootprintTextFieldValue,
                    onSavedCarbonFootprintChange = onSavedCarbonFootprintChange,
                    onPublishClicked = onPublishClicked,
                )
            }
        }
    }

    if (showModalBottomSheet) {
        MediaSelectionBottomSheet(
            onDismiss = { showModalBottomSheet = false },
            onCameraSelected = {
                showModalBottomSheet = false
                onTakePhoto()
            },
            onGallerySelected = {
                showModalBottomSheet = false
                val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "image/* video/*"
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                }
                onMediaSelected(intent)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MediaSelectionBottomSheet(
    onDismiss: () -> Unit,
    onCameraSelected: () -> Unit,
    onGallerySelected: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally,
        ) {
            TextButton(onClick = onCameraSelected) {
                Text("Take Photo/Video")
            }
            TextButton(onClick = onGallerySelected) {
                Text("Choose from Gallery")
            }
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    }
}

@Composable
@LaBrocantePreview
private fun Preview() {
    LaBrocanteTheme {
        PromptContent(
            mediaUris = emptyList(),
            uiModel = PromptUiModel(
                uiState = PromptUiModel.UiState.Content(
                    announcement = PromptUiModel.UiState.Content.Announcement(
                        title = "Title",
                        description = "Description",
                        category = "Category",
                        price = "100",
                        savedCarbonFootprint = "0.5",
                    ),
                ),
            ),
        )
    }
}
