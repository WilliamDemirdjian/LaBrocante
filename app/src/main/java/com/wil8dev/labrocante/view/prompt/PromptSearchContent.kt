package com.wil8dev.labrocante.view.prompt

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.wil8dev.labrocante.view.core.compose.LaBrocanteButton
import com.wil8dev.labrocante.view.core.compose.LaBrocanteText
import com.wil8dev.labrocante.view.core.compose.LaBrocanteTextField

@Composable
fun PromptSearchContent(
    modifier: Modifier = Modifier,
    uiState: PromptUiModel.UiState.Content,
    descriptionTextFieldValue: TextFieldValue,
    onDescriptionChange: (value: TextFieldValue) -> Unit,
    priceTextFieldValue: TextFieldValue,
    onPriceChange: (value: TextFieldValue) -> Unit,
    savedCarbonFootprintTextFieldValue: TextFieldValue,
    onSavedCarbonFootprintChange: (value: TextFieldValue) -> Unit,
    mediaUris: List<Uri>,
    onShowMediaOptions: () -> Unit,
    onRemoveMedia: (Uri) -> Unit,
    onShowResults: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = descriptionTextFieldValue,
            onValueChange = onDescriptionChange,
            label = "Description (mandatory)",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = priceTextFieldValue,
            onValueChange = onPriceChange,
            keyboardType = KeyboardType.Number,
            label = "Maximal price ($) (optional)",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = savedCarbonFootprintTextFieldValue,
            onValueChange = onSavedCarbonFootprintChange,
            keyboardType = KeyboardType.Number,
            keyboardImeAction = ImeAction.Done,
            label = "Maximum carbon footprint (kgCO2) (optional)",
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddMedia(
            mediaUris = mediaUris,
            maximumMediaCount = 1,
            onRemoveMedia = onRemoveMedia,
            onShowMediaOptions = onShowMediaOptions,
        )
        Spacer(modifier = Modifier.height(24.dp))
        LaBrocanteButton(
            text = "Search for item",
            onClick = onShowResults,
        )
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedVisibility(
            visible = uiState.suggestedAnnouncement != null,
        ) {
            uiState.suggestedAnnouncement?.let { suggestedAnnouncement ->
                ProposedResult(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    suggestedAnnouncement = suggestedAnnouncement,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ProposedResult(
    modifier: Modifier = Modifier,
    suggestedAnnouncement: PromptUiModel.UiState.Content.SuggestedAnnouncement,
) {
    Column(
        modifier = modifier,
    ) {
        LaBrocanteText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Suggested item",
        )
        Spacer(modifier = Modifier.height(16.dp))
        suggestedAnnouncement.description?.let { description ->
            LaBrocanteText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = description,
            )
        }
    }
}
