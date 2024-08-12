package com.wil8dev.labrocante.view.assistant

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wil8dev.labrocante.view.core.compose.LaBrocantePreview
import com.wil8dev.labrocante.view.core.compose.LaBrocanteScaffold
import com.wil8dev.labrocante.view.core.compose.getHeightPx
import com.wil8dev.labrocante.view.core.compose.getWidthPx
import com.wil8dev.labrocante.view.core.compose.theme.LaBrocanteTheme
import com.wil8dev.labrocante.view.home.Announcement
import kotlin.random.Random

@Composable
fun AssistantContent(
    modifier: Modifier = Modifier,
    onSellClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
) {
    LaBrocanteScaffold(
        modifier = modifier
            .fillMaxSize(),
        topBarText = "Try the assistant",
    ) { innerPadding ->
        val screenWidthPx = getWidthPx()
        val screenHeightPx = getHeightPx()
        val infiniteTransition = rememberInfiniteTransition(label = "backgroundInfiniteTransition")
        val backgroundColor by infiniteTransition.animateColor(
            initialValue = Color(0xFF2C6E49),
            targetValue = Color(0xFF22BB65),
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "backgroundColorTransition",
        )
        val backgroundRadius by infiniteTransition.animateFloat(
            initialValue = screenHeightPx,
            targetValue = screenWidthPx * 2,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "backgroundRadiusTransition",
        )
        val backgroundHorizontalOffset by infiniteTransition.animateFloat(
            initialValue = screenWidthPx / 2,
            targetValue = Random.nextFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "backgroundXOffsetTransition",
        )
        val backgroundVerticalOffset by infiniteTransition.animateFloat(
            initialValue = screenHeightPx / 2,
            targetValue = Random.nextFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "backgroundYOffsetTransition",
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            backgroundColor,
                            Color.White,
                        ),
                        center = Offset(x = backgroundHorizontalOffset, y = backgroundVerticalOffset),
                        radius = backgroundRadius,
                    )
                )
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // TODO Radio Buttons (find now ou sell now) send to PromptView
            Article(
                title = "Sell with the assistant",
                description = "Do you have a piece of furniture that you no longer need?" +
                    "\n" +
                    "Take a picture and we will sell it for you, including negociation, payment and delivery.",
                ctaLabel = "Sell now 🚀",
                onCLick = onSellClicked,
            )
            Article(
                title = "Search with the assistant",
                description = "Are you looking for a piece of handcrafted furniture that will look great in your living room?" +
                    "\n" +
                    "Tell us what would make you happy or take a photo of your surroundings and we'll find the perfect match.",
                ctaLabel = "Find now ✨",
                onCLick = onSearchClicked,
            )
        }
    }
}

@Composable
private fun Article(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    ctaLabel: String,
    onCLick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xD3EEEEEE))
            .clickable(onClick = onCLick)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = description,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Announcement(
            title = ctaLabel,
            shape = CircleShape,
            onClick = onCLick,
        )
    }
}

@Composable
@LaBrocantePreview
private fun Preview() {
    LaBrocanteTheme {
        AssistantContent()
    }
}
