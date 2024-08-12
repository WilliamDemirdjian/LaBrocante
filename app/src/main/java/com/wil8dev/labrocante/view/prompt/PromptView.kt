package com.wil8dev.labrocante.view.prompt

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PromptView(
    modifier: Modifier = Modifier,
    isSelling: Boolean,
    viewModel: PromptViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.updateIsSelling(isSelling)
    }
    val uiModel by viewModel.uiModelFlow.collectAsStateWithLifecycle()
    val mediaUris by viewModel.mediaUris.collectAsState()
    val context = LocalContext.current
    var tempPhotoUri by remember { mutableStateOf<Uri?>(null) }
    val mediaLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uris = mutableListOf<Uri>()
            result.data?.let { intent ->
                if (intent.clipData != null) {
                    val count = intent.clipData?.itemCount ?: 0
                    for (i in 0 until count) {
                        intent.clipData?.getItemAt(i)?.uri?.let { uris.add(it) }
                    }
                } else {
                    intent.data?.let { uris.add(it) }
                }
            }
            tempPhotoUri?.let { uris.add(it) }

            viewModel.onMediaSelected(uris + mediaUris)
        }
    }

    fun createTempImageFile(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
    }

    fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file,
        )
    }

    LaunchedEffect(uiModel.navigation) {
        when (uiModel.navigation) {
            is PromptUiModel.Navigation.None -> {}
        }
    }

    PromptContent(
        modifier = modifier,
        uiModel = uiModel,
        onOptionSelected = { viewModel.updateIsSelling(it) },
        onShowResults = {
            viewModel.fillAnnouncement(context = context)
        },
        mediaUris = mediaUris,
        onMediaSelected = { intent -> mediaLauncher.launch(intent) },
        onTakePhoto = {
            val photoFile = createTempImageFile()
            val photoUri = getUriForFile(photoFile)
            tempPhotoUri = photoUri
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            }
            mediaLauncher.launch(cameraIntent)
        },
        onRemoveMedia = { uri ->
            viewModel.onRemoveMedia(uri)
        },
        userDescriptionTextFieldValue = viewModel.userDescriptionTextFieldValue,
        onUserDescriptionChange = viewModel::updateUserDescription,
        titleTextFieldValue = viewModel.titleTextFieldValue,
        onTitleChange = viewModel::updateTitle,
        descriptionTextFieldValue = viewModel.descriptionTextFieldValue,
        onDescriptionChange = viewModel::updateDescription,
        categoryTextFieldValue = viewModel.categoryTextFieldValue,
        onCategoryChange = viewModel::updateCategory,
        priceTextFieldValue = viewModel.priceTextFieldValue,
        onPriceChange = viewModel::updatePrice,
        savedCarbonFootprintTextFieldValue = viewModel.savedCarbonFootprintTextFieldValue,
        onSavedCarbonFootprintChange = viewModel::updateSavedCarbonFootprint,
        onPublishClicked = viewModel::publishAnnouncement,
    )
}
