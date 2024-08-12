package com.wil8dev.labrocante.view.prompt

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.wil8dev.labrocante.view.core.compose.LaBrocantePreview
import com.wil8dev.labrocante.view.core.compose.theme.LaBrocanteTheme

@Composable
fun PromptLoadedContent(
    modifier: Modifier = Modifier,
    uiState: PromptUiModel.UiState.Content,
    onOptionSelected: (Boolean) -> Unit,
    mediaUris: List<Uri>,
    onShowModalBottomSheet: () -> Unit,
    onShowResults: () -> Unit,
    onRemoveMedia: (Uri) -> Unit,
    userDescriptionTextFieldValue: TextFieldValue,
    onUserDescriptionChange: (value: TextFieldValue) -> Unit,
    titleTextFieldValue: TextFieldValue,
    onTitleChange: (value: TextFieldValue) -> Unit,
    descriptionTextFieldValue: TextFieldValue,
    onDescriptionChange: (value: TextFieldValue) -> Unit,
    categoryTextFieldValue: TextFieldValue,
    onCategoryChange: (value: TextFieldValue) -> Unit,
    priceTextFieldValue: TextFieldValue,
    onPriceChange: (value: TextFieldValue) -> Unit,
    savedCarbonFootprintTextFieldValue: TextFieldValue,
    onSavedCarbonFootprintChange: (value: TextFieldValue) -> Unit,
    onPublishClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        RadioButtons(
            modifier = Modifier.padding(horizontal = 16.dp),
            isSelling = uiState.isSelling,
            onOptionSelected = onOptionSelected,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.isSelling) {
            PromptSellingContent(
                modifier = Modifier.fillMaxWidth(),
                uiState = uiState,
                mediaUris = mediaUris,
                onShowMediaOptions = onShowModalBottomSheet,
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
        } else {
            PromptSearchContent(
                modifier = Modifier.fillMaxWidth(),
                uiState = uiState,
                descriptionTextFieldValue = descriptionTextFieldValue,
                onDescriptionChange = onDescriptionChange,
                priceTextFieldValue = priceTextFieldValue,
                onPriceChange = onPriceChange,
                savedCarbonFootprintTextFieldValue = savedCarbonFootprintTextFieldValue,
                onSavedCarbonFootprintChange = onSavedCarbonFootprintChange,
                mediaUris = mediaUris,
                onShowMediaOptions = onShowModalBottomSheet,
                onRemoveMedia = onRemoveMedia,
                onShowResults = onShowResults,
            )
        }
    }
}

@Composable
private fun RadioButtons(
    modifier: Modifier = Modifier,
    isSelling: Boolean,
    onOptionSelected: (Boolean) -> Unit,
) {
    val radioOptions = listOf(
        "I'm selling",
        "I'm looking for",
    )
    var selectedOption by remember {
        mutableStateOf(if (isSelling) radioOptions[0] else radioOptions[1])
    }
    Column(
        modifier = modifier,
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            selectedOption = text
                            onOptionSelected(text == radioOptions[0])
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        selectedOption = text
                        onOptionSelected(text == radioOptions[0])
                    },
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                )
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
