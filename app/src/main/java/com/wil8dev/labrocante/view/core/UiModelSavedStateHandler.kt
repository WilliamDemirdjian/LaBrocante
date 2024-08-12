package com.wil8dev.labrocante.view.core

import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty

class UiModelSavedStateHandler<T : Parcelable>(
    private val savedStateHandle: SavedStateHandle,
    defaultUiModel: T,
) : UiModelHandler<T> {

    private val savedStateKey = defaultUiModel::class.java.simpleName
    private val uiModelUpdater = ThreadSafeUiModelUpdater()
    override val uiModelFlow = savedStateHandle.getStateFlow(savedStateKey, defaultUiModel)

    override suspend fun updateUiModel(getNewValue: (currentValue: T) -> T) {
        uiModelUpdater.updateUiModel(uiModelFlow, getNewValue) { newValue ->
            savedStateHandle[savedStateKey] = newValue
        }
    }
}

class UiModelHandlerFactory @Inject constructor() {

    fun <T : Parcelable> buildSavedStateUiStateHandler(
        savedStateHandle: SavedStateHandle,
        defaultUiModel: T,
    ): UiModelHandler<T> = UiModelSavedStateHandler(
        savedStateHandle = savedStateHandle,
        defaultUiModel = defaultUiModel,
    )
}

interface UiModelHandler<T> {

    val uiModelFlow: StateFlow<T>
    suspend fun updateUiModel(getNewValue: (currentValue: T) -> T)
}

class ThreadSafeUiModelUpdater {

    private val mutex = Mutex()

    suspend fun <T> updateUiModel(
        flow: StateFlow<T>,
        getNewValue: (currentValue: T) -> T,
        updateValue: (T) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            mutex.withLock {
                val newValue = getNewValue(flow.value)
                withContext(Dispatchers.Main) {
                    updateValue(newValue)
                }
            }
        }
    }
}

@OptIn(SavedStateHandleSaveableApi::class)
fun SavedStateHandle.textFieldValueSaveable(): PropertyDelegateProvider<Any?, ReadWriteProperty<Any?, TextFieldValue>> =
    saveable(
        stateSaver = TextFieldValue.Saver,
    ) {
        mutableStateOf(TextFieldValue())
    }
