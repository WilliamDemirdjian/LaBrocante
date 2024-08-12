package com.wil8dev.labrocante.view.prompt

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.wil8dev.labrocante.BuildConfig
import com.wil8dev.labrocante.view.core.UiModelHandlerFactory
import com.wil8dev.labrocante.view.core.textFieldValueSaveable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class PromptViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    uiModelHandlerFactory: UiModelHandlerFactory,
) : ViewModel() {

    private val uiModelHandler = uiModelHandlerFactory.buildSavedStateUiStateHandler(
        savedStateHandle = savedStateHandle,
        defaultUiModel = PromptUiModel(),
    )
    val uiModelFlow get() = uiModelHandler.uiModelFlow
    private val _mediaUris = MutableStateFlow<List<Uri>>(emptyList())
    val mediaUris: StateFlow<List<Uri>> get() = _mediaUris
    var userDescriptionTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    var titleTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    var descriptionTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    var categoryTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    var priceTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    var savedCarbonFootprintTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey,
    )

    fun fillAnnouncement(
        context: Context,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val uiState = uiModelFlow.value.uiState
            if (uiState !is PromptUiModel.UiState.Content) {
                return@launch
            }
            try {
                println("LaBrocante - Generating content...")
                val instruction = if (uiState.isSelling) {
                    "You're the assistant in a second-hand goods sales application. " +
                        "Please analyze the picture, localize the main object and help people describe the object, " +
                        "Define a relevant title for the ad using {{title=My title}}." +
                        "Define the description of the ad using {{description=My description}}." +
                        "Estimate the price of the ad using {{price=My price}} (as Integer, in dollars, the price is approximate," +
                        " there are closes to indicate that the price is not guaranteed)." +
                        "Define the category of the ad using {{category=My category}}." +
                        "Define the carbon footprint (as Float) saved by buying this object second-hand rather than new (in kg of" +
                        " co2), using {{saved_carbon=My carbon footprint}}. " +
                        "User description to help you: ${userDescriptionTextFieldValue.text}"
                } else {
                    "You are an assistant in an application to help users find second-hand items. " +
                        "The user provided the following details about what they are looking for: ${descriptionTextFieldValue.text}. " +
                        "If the user has provided any images or videos, analyze them to refine the search. " +
                        "If the user has provided a maximum price, consider it while finding a suitable item : " +
                        "${priceTextFieldValue.text} $. " +
                        "The user also cares about the carbon footprint, aiming for a maximum of " +
                        ": ${savedCarbonFootprintTextFieldValue.text} kg CO2."
                }
                val response = generativeModel.generateContent(
                    content {
                        mediaUris.value.forEach { uri ->
                            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                            val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)
                            inputStream?.close()
                            if (bitmap != null) {
                                image(BitmapDrawable(context.resources, bitmap).bitmap)
                            }
                        }
                        text(instruction)
                    }
                )
                response.text?.let { outputContent ->
                    println("LaBrocante - Generated content : $outputContent")
                    if (uiState.isSelling) {
                        mapAndSendSellContent(outputContent)
                    } else {
                        mapAndSendSearchContent(outputContent)
                    }
                }
            } catch (e: Exception) {
                println(("LaBrocante - Error during generating content" + e.localizedMessage))
            }
        }
    }

    private fun mapAndSendSellContent(
        content: String,
    ) {
        val contentMap = content
            .split("\n")
            .associate { line ->
                val parts = line
                    .removeSurrounding("{{", "}}")
                    .split("=")
                parts.first() to parts.last()
            }
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                val announcement = PromptUiModel.UiState.Content.Announcement(
                    title = contentMap["title"] ?: "",
                    description = contentMap["description"] ?: "",
                    category = contentMap["category"] ?: "",
                    price = contentMap["price"] ?: "",
                    savedCarbonFootprint = contentMap["saved_carbon"] ?: "",
                )
                announcement.title?.let { updateTitle(TextFieldValue(it)) }
                announcement.description?.let { updateDescription(TextFieldValue(it)) }
                announcement.category?.let { updateCategory(TextFieldValue(it)) }
                announcement.price?.let { updatePrice(TextFieldValue(it)) }
                announcement.savedCarbonFootprint?.let { updateSavedCarbonFootprint(TextFieldValue(it)) }
                uiModel.copy(
                    uiState = PromptUiModel.UiState.Content(
                        announcement = announcement,
                    ),
                )
            }
        }
    }

    private fun mapAndSendSearchContent(
        content: String,
    ) {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(
                    uiState = PromptUiModel.UiState.Content(
                        suggestedAnnouncement = PromptUiModel.UiState.Content.SuggestedAnnouncement(
                            description = content,
                        ),
                    ),
                )
            }
        }
    }

    fun updateIsSelling(isSelling: Boolean) {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(
                    uiState = PromptUiModel.UiState.Content(
                        isSelling = isSelling,
                    ),
                )
            }
        }
    }

    fun onMediaSelected(uris: List<Uri>) {
        _mediaUris.update { uris }
    }

    fun onRemoveMedia(uri: Uri) {
        _mediaUris.update { currentUris ->
            currentUris.filterNot { it == uri }
        }
    }

    fun updateUserDescription(value: TextFieldValue) {
        userDescriptionTextFieldValue = value
    }

    fun updateTitle(value: TextFieldValue) {
        titleTextFieldValue = value
    }

    fun updateDescription(value: TextFieldValue) {
        descriptionTextFieldValue = value
    }

    fun updateCategory(value: TextFieldValue) {
        categoryTextFieldValue = value
    }

    fun updatePrice(value: TextFieldValue) {
        priceTextFieldValue = value
    }

    fun updateSavedCarbonFootprint(value: TextFieldValue) {
        savedCarbonFootprintTextFieldValue = value
    }

    fun publishAnnouncement() {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(
                    navigation = PromptUiModel.Navigation.None,
                )
            }
        }
    }
}
