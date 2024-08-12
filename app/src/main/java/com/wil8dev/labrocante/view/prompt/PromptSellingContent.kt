package com.wil8dev.labrocante.view.prompt

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wil8dev.labrocante.view.core.compose.LaBrocanteButton
import com.wil8dev.labrocante.view.core.compose.LaBrocanteTextField
import com.wil8dev.labrocante.view.core.compose.LaBrocanteTextInfo

@Composable
fun PromptSellingContent(
    modifier: Modifier = Modifier,
    uiState: PromptUiModel.UiState.Content,
    mediaUris: List<Uri>,
    onShowMediaOptions: () -> Unit,
    onRemoveMedia: (Uri) -> Unit,
    userDescriptionTextFieldValue: TextFieldValue,
    onUserDescriptionChange: (value: TextFieldValue) -> Unit,
    onShowResults: () -> Unit,
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
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        AddMedia(
            mediaUris = mediaUris,
            maximumMediaCount = 6,
            onShowMediaOptions = onShowMediaOptions,
            onRemoveMedia = onRemoveMedia,
        )
        Spacer(modifier = Modifier.height(24.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = userDescriptionTextFieldValue,
            onValueChange = { newValue -> onUserDescriptionChange(newValue) },
            label = "Add a few words? (optional)",
            keyboardImeAction = ImeAction.Done,
        )
        Spacer(modifier = Modifier.height(24.dp))
        LaBrocanteButton(
            text = "Generate announcement",
            onClick = onShowResults,
        )
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedVisibility(
            visible = uiState.announcement != null,
        ) {
            uiState.announcement?.let { announcement ->
                ProposedResult(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    announcement = announcement,
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
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ProposedResult(
    modifier: Modifier = Modifier,
    announcement: PromptUiModel.UiState.Content.Announcement,
    titleTextFieldValue: TextFieldValue = TextFieldValue(announcement.title ?: ""),
    onTitleChange: (value: TextFieldValue) -> Unit = {},
    descriptionTextFieldValue: TextFieldValue = TextFieldValue(announcement.description ?: ""),
    onDescriptionChange: (value: TextFieldValue) -> Unit = {},
    categoryTextFieldValue: TextFieldValue = TextFieldValue(announcement.category ?: ""),
    onCategoryChange: (value: TextFieldValue) -> Unit = {},
    priceTextFieldValue: TextFieldValue = TextFieldValue(announcement.price ?: ""),
    onPriceChange: (value: TextFieldValue) -> Unit = {},
    savedCarbonFootprintTextFieldValue: TextFieldValue = TextFieldValue(announcement.savedCarbonFootprint ?: ""),
    onSavedCarbonFootprintChange: (value: TextFieldValue) -> Unit = {},
    onPublishClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Suggested by Google Gemini 🚀",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = titleTextFieldValue,
            onValueChange = { newValue -> onTitleChange(newValue) },
            label = "Title",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = descriptionTextFieldValue,
            onValueChange = { newValue -> onDescriptionChange(newValue) },
            label = "Description",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = categoryTextFieldValue,
            onValueChange = { newValue -> onCategoryChange(newValue) },
            label = "Category",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = savedCarbonFootprintTextFieldValue,
            onValueChange = { newValue -> onSavedCarbonFootprintChange(newValue) },
            keyboardType = KeyboardType.Number,
            label = "Saved Carbon Footprint (kg CO2e)",
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = priceTextFieldValue,
            onValueChange = { newValue -> onPriceChange(newValue) },
            keyboardType = KeyboardType.Number,
            label = "Price ($)",
        )
        Spacer(modifier = Modifier.height(8.dp))
        LaBrocanteTextInfo(
            modifier = Modifier.fillMaxWidth(),
            text = "Always verify the suggested price, as it is provided for informational purposes only and " +
                "is not guaranteed. It may be subject to change, either up or down. La Brocante disclaims all " +
                "liability for any discrepancies between the suggested price and the final price and will not be " +
                "held responsible for any consequences arising from such differences.",
            icon = Icons.Default.Warning,
            backgroundColor = Color(0xFFA70B0B),
        )
        Spacer(modifier = Modifier.height(16.dp))
        LaBrocanteButton(
            text = "Publish",
            onClick = onPublishClicked,
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}
