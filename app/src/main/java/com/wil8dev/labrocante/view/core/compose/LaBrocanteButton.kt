package com.wil8dev.labrocante.view.core.compose

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LaBrocanteButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2C6E49), // TODO color file
            contentColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = onClick,
    ) {
        Text(
            text = text,
            color = if (enabled) Color.White else Color.Gray,
            textAlign = TextAlign.Center,
        )
    }
}
