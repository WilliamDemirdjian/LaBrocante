package com.wil8dev.labrocante.view.core.compose.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberCamera(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "camera",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f,
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(20.083f, 15.042f)
                horizontalLineToRelative(12.792f)
                quadToRelative(-1.167f, -3f, -3.583f, -5.25f)
                quadToRelative(-2.417f, -2.25f, -5.584f, -3.084f)
                lineToRelative(-4.166f, 7.334f)
                quadToRelative(-0.209f, 0.333f, -0.021f, 0.666f)
                quadToRelative(0.187f, 0.334f, 0.562f, 0.334f)
                close()
                moveToRelative(-5.5f, 2.375f)
                quadToRelative(0.167f, 0.333f, 0.542f, 0.333f)
                reflectiveQuadToRelative(0.583f, -0.333f)
                lineToRelative(6.417f, -11.042f)
                quadToRelative(-0.458f, -0.083f, -1.042f, -0.104f)
                quadTo(20.5f, 6.25f, 20f, 6.25f)
                quadToRelative(-2.917f, 0f, -5.354f, 1.042f)
                quadToRelative(-2.438f, 1.041f, -4.354f, 2.916f)
                close()
                moveTo(6.667f, 23.5f)
                horizontalLineToRelative(8.375f)
                quadToRelative(0.375f, 0f, 0.583f, -0.312f)
                quadToRelative(0.208f, -0.313f, 0f, -0.688f)
                lineTo(9.292f, 11.333f)
                quadToRelative(-1.5f, 1.792f, -2.271f, 4.021f)
                quadTo(6.25f, 17.583f, 6.25f, 20f)
                quadToRelative(0f, 0.875f, 0.083f, 1.771f)
                quadToRelative(0.084f, 0.896f, 0.334f, 1.729f)
                close()
                moveToRelative(9.666f, 9.792f)
                lineTo(20.542f, 26f)
                quadToRelative(0.208f, -0.375f, 0.02f, -0.708f)
                quadToRelative(-0.187f, -0.334f, -0.562f, -0.334f)
                horizontalLineTo(7.167f)
                quadToRelative(1.125f, 3f, 3.562f, 5.25f)
                quadToRelative(2.438f, 2.25f, 5.604f, 3.084f)
                close()
                moveTo(20f, 33.75f)
                quadToRelative(2.917f, 0f, 5.354f, -1.042f)
                quadToRelative(2.438f, -1.041f, 4.354f, -2.916f)
                lineTo(25.5f, 22.583f)
                quadToRelative(-0.208f, -0.333f, -0.604f, -0.333f)
                reflectiveQuadToRelative(-0.604f, 0.333f)
                lineToRelative(-6.417f, 11.042f)
                quadToRelative(0.5f, 0.083f, 1.042f, 0.104f)
                quadToRelative(0.541f, 0.021f, 1.083f, 0.021f)
                close()
                moveToRelative(10.708f, -5.083f)
                quadToRelative(1.375f, -1.709f, 2.209f, -3.979f)
                quadToRelative(0.833f, -2.271f, 0.833f, -4.688f)
                quadToRelative(0f, -0.917f, -0.083f, -1.792f)
                quadToRelative(-0.084f, -0.875f, -0.292f, -1.75f)
                horizontalLineTo(25f)
                quadToRelative(-0.375f, 0f, -0.604f, 0.334f)
                quadToRelative(-0.229f, 0.333f, -0.021f, 0.666f)
                close()
                moveTo(20f, 20f)
                close()
                moveToRelative(0f, 16.375f)
                quadToRelative(-3.375f, 0f, -6.354f, -1.292f)
                quadToRelative(-2.979f, -1.291f, -5.208f, -3.521f)
                quadToRelative(-2.23f, -2.229f, -3.521f, -5.208f)
                quadTo(3.625f, 23.375f, 3.625f, 20f)
                quadToRelative(0f, -3.417f, 1.292f, -6.375f)
                quadToRelative(1.291f, -2.958f, 3.521f, -5.208f)
                quadToRelative(2.229f, -2.25f, 5.208f, -3.521f)
                reflectiveQuadTo(20f, 3.625f)
                quadToRelative(3.417f, 0f, 6.375f, 1.292f)
                quadToRelative(2.958f, 1.291f, 5.208f, 3.521f)
                quadToRelative(2.25f, 2.229f, 3.521f, 5.187f)
                reflectiveQuadTo(36.375f, 20f)
                quadToRelative(0f, 3.375f, -1.292f, 6.354f)
                quadToRelative(-1.291f, 2.979f, -3.521f, 5.208f)
                quadToRelative(-2.229f, 2.23f, -5.187f, 3.521f)
                quadToRelative(-2.958f, 1.292f, -6.375f, 1.292f)
                close()
            }
        }.build()
    }
}
