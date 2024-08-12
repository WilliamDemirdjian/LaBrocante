package com.wil8dev.labrocante.view.account

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.wil8dev.labrocante.view.core.UiModelHandlerFactory
import com.wil8dev.labrocante.view.core.textFieldValueSaveable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AccountViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    uiModelHandlerFactory: UiModelHandlerFactory,
) : ViewModel() {

    private val uiModelHandler = uiModelHandlerFactory.buildSavedStateUiStateHandler(
        savedStateHandle = savedStateHandle,
        defaultUiModel = AccountUiModel(),
    )
    val uiModelFlow get() = uiModelHandler.uiModelFlow
    var emailTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set
    var passwordTextFieldValue by savedStateHandle.textFieldValueSaveable()
        private set

    @Inject
    @Named("firebaseAuth")
    lateinit var firebaseAuth: FirebaseAuth

    init {
        emailTextFieldValue = TextFieldValue("wil.8dev@gmail.com")
        passwordTextFieldValue = TextFieldValue("Tototo")
    }

    fun updateEmail(value: TextFieldValue) {
        emailTextFieldValue = value
    }

    fun updatePassword(value: TextFieldValue) {
        passwordTextFieldValue = value
    }

    fun login() {
        viewModelScope.launch {
            signIn(
                onLoggedIn = ::confirmConnection,
                onFailed = ::displayError,
            )
        }
    }

    private fun signIn(
        onLoggedIn: () -> Unit,
        onFailed: () -> Unit,
    ) {
        try {
            firebaseAuth.signInWithEmailAndPassword(
                emailTextFieldValue.text.trim(),
                passwordTextFieldValue.text.trim(),
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    emailTextFieldValue = TextFieldValue("")
                    passwordTextFieldValue = TextFieldValue("")
                    onLoggedIn()
                } else {
                    onFailed()
                }
            }
        } catch (e: Exception) {
            onFailed()
            println("LaBrocante LoggedIn - failed: ${e.message}")
        }
    }

    private fun displayError() {
        viewModelScope.launch {
            if (uiModelFlow.value.uiState is AccountUiModel.UiState.Content.Disconnected) {
                uiModelHandler.updateUiModel { uiModel ->
                    uiModel.copy(
                        uiState = AccountUiModel.UiState.Content.Disconnected(
                            hasError = true,
                        ),
                    )
                }
            }
        }
    }

    private fun confirmConnection() {
        viewModelScope.launch {
            uiModelHandler.updateUiModel { uiModel ->
                uiModel.copy(
                    uiState = AccountUiModel.UiState.Content.Connected(
                        userName = firebaseAuth.currentUser?.email ?: "",
                    ),
                )
            }
        }
    }
}
