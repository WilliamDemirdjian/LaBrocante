package com.wil8dev.labrocante.view.core.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LaBrocanteTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    label: String,
    onValueChange: (value: TextFieldValue) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    keyboardAutocorrect: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardImeAction: ImeAction = ImeAction.Next,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    regex: Regex? = null,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (
                regex != null &&
                it.text.all { char ->
                    char.toString().matches(regex)
                }
            ) {
                onValueChange(it)
            } else if (regex == null) {
                onValueChange(it)
            }
        },
        label = { Text(text = label) },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = keyboardCapitalization,
            autoCorrect = keyboardAutocorrect,
            keyboardType = keyboardType,
            imeAction = keyboardImeAction,
            platformImeOptions = PlatformImeOptions("flagNoPersonalizedLearning"),
        ),
        isError = isError,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        trailingIcon = {
            if (value.text.isNotEmpty()) {
                IconButton(
                    onClick = { onValueChange(TextFieldValue()) },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}
