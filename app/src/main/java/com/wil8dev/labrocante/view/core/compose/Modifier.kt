package com.wil8dev.labrocante.view.core.compose

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.onlyIf(
    value: Boolean,
    action: @Composable Modifier.() -> Modifier,
): Modifier = composed {
    if (value) {
        action()
    } else {
        this
    }
}

@Suppress("UnnecessaryComposedModifier")
fun <T> Modifier.onlyIfNotNull(
    value: T?,
    action: @Composable Modifier.(T) -> Modifier,
): Modifier = composed {
    if (value != null) {
        action(value)
    } else {
        this
    }
}

@Suppress("UnnecessaryComposedModifier")
fun <T> Modifier.onlyIfNotNull(
    vararg values: T?,
    action: @Composable Modifier.(List<T>) -> Modifier,
): Modifier = composed {
    if (values.all { it != null }) {
        action(values.filterNotNull())
    } else {
        this
    }
}

@Composable
fun Modifier.animatedGradientBorder(
    shape: Shape = RectangleShape,
    onClick: () -> Unit = {},
): Modifier {
    val colors = listOf(
        Color(0xFF5B8CFF),
        Color(0xFFC5DBFA),
        Color(0xFFFDADEE),
        Color(0xFFFDADEE),
        Color(0xFFC5DBFA),
        Color(0xFF5B8CFF),
    )
    val brush = Brush.sweepGradient(colors)
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val degreesAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "degreesAnimation",
    )
    return this
        .clip(shape)
        .clickable(onClick = onClick)
        .padding(2.dp)
        .drawWithContent {
            rotate(degreesAnimation) {
                drawCircle(
                    brush = brush,
                    radius = size.width,
                    blendMode = BlendMode.SrcIn,
                )
            }
            drawContent()
        }
}
