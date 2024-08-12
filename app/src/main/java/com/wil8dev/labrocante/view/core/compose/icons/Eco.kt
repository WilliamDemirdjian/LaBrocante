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
fun rememberEco(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "eco",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
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
                moveTo(9.083f, 32.542f)
                quadToRelative(-1.791f, -1.834f, -2.812f, -4.25f)
                quadToRelative(-1.021f, -2.417f, -1.021f, -5f)
                quadToRelative(0f, -2.875f, 1.021f, -5.375f)
                reflectiveQuadToRelative(3.146f, -4.584f)
                quadToRelative(1.416f, -1.458f, 3.583f, -2.437f)
                quadToRelative(2.167f, -0.979f, 5.083f, -1.542f)
                quadToRelative(2.917f, -0.562f, 6.563f, -0.687f)
                reflectiveQuadToRelative(8.062f, 0.166f)
                quadToRelative(0.334f, 4.334f, 0.23f, 7.979f)
                quadToRelative(-0.105f, 3.646f, -0.646f, 6.563f)
                quadToRelative(-0.542f, 2.917f, -1.563f, 5.146f)
                reflectiveQuadToRelative(-2.521f, 3.687f)
                quadToRelative(-2.083f, 2.125f, -4.52f, 3.167f)
                quadToRelative(-2.438f, 1.042f, -5.105f, 1.042f)
                quadToRelative(-2.791f, 0f, -5.187f, -0.979f)
                quadToRelative(-2.396f, -0.98f, -4.313f, -2.896f)
                close()
                moveToRelative(4.25f, -0.167f)
                quadToRelative(1.084f, 0.708f, 2.438f, 1.063f)
                quadToRelative(1.354f, 0.354f, 2.812f, 0.354f)
                quadToRelative(2.084f, 0f, 4.105f, -0.875f)
                quadToRelative(2.02f, -0.875f, 3.687f, -2.584f)
                quadToRelative(0.958f, -1f, 1.729f, -2.645f)
                quadToRelative(0.771f, -1.646f, 1.292f, -4f)
                quadToRelative(0.521f, -2.355f, 0.729f, -5.438f)
                quadToRelative(0.208f, -3.083f, 0.042f, -6.958f)
                quadToRelative(-3.292f, -0.084f, -6.188f, 0.041f)
                reflectiveQuadToRelative(-5.291f, 0.584f)
                quadToRelative(-2.396f, 0.458f, -4.271f, 1.229f)
                quadToRelative(-1.875f, 0.771f, -3.042f, 1.979f)
                quadToRelative(-1.75f, 1.792f, -2.625f, 3.75f)
                reflectiveQuadToRelative(-0.875f, 3.875f)
                quadToRelative(0f, 2.125f, 0.875f, 4.188f)
                quadToRelative(0.875f, 2.062f, 1.958f, 3.229f)
                quadToRelative(2f, -3.792f, 4.959f, -6.938f)
                quadToRelative(2.958f, -3.146f, 6.458f, -5.021f)
                quadToRelative(-3.375f, 2.917f, -5.583f, 6.396f)
                quadToRelative(-2.209f, 3.479f, -3.209f, 7.771f)
                close()
                moveToRelative(0f, 0f)
                close()
                moveToRelative(0f, 0f)
                close()
            }
        }.build()
    }
}
