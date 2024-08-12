package com.wil8dev.labrocante.view.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wil8dev.labrocante.R
import com.wil8dev.labrocante.view.core.compose.LaBrocanteButton
import com.wil8dev.labrocante.view.core.compose.LaBrocantePreview
import com.wil8dev.labrocante.view.core.compose.theme.LaBrocanteTheme

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFD0F1BF),
                    )
                )
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF2C6E49),
        )
        Text(
            text = "La Brocante",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF2C6E49),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Reduce your carbonfootprint ")
                }
                append("by giving a second life to objects.")
            },
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF2C6E49),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Every year, ")
                }
                append("Earth Overshoot Day arrives earlier, signaling that we consume more than our planet can renew.")
            },
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF2C6E49),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("By choosing second-hand over new, ")
                }
                append("you are helping to protect our planet and preserve its resources for future generations.")
            },
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF2C6E49),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Consume better, live better.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF2C6E49),
        )
        Spacer(modifier = Modifier.height(24.dp))
        LaBrocanteButton(
            text = "Get started",
            onClick = onClose,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@LaBrocantePreview
private fun Preview() {
    LaBrocanteTheme {
        WelcomeContent()
    }
}
