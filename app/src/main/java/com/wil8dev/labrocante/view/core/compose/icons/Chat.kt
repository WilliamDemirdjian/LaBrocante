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
fun rememberChat(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "chat",
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
                moveTo(11.5f, 23.208f)
                horizontalLineToRelative(10.292f)
                quadToRelative(0.5f, 0f, 0.875f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.958f)
                quadToRelative(0f, -0.542f, -0.375f, -0.917f)
                reflectiveQuadToRelative(-0.917f, -0.375f)
                horizontalLineTo(11.458f)
                quadToRelative(-0.541f, 0f, -0.916f, 0.375f)
                reflectiveQuadToRelative(-0.375f, 0.959f)
                quadToRelative(0f, 0.541f, 0.375f, 0.916f)
                reflectiveQuadToRelative(0.958f, 0.375f)
                close()
                moveToRelative(0f, -5.208f)
                horizontalLineToRelative(17.083f)
                quadToRelative(0.5f, 0f, 0.875f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.958f)
                quadToRelative(0f, -0.542f, -0.395f, -0.917f)
                quadToRelative(-0.396f, -0.375f, -0.896f, -0.375f)
                horizontalLineTo(11.458f)
                quadToRelative(-0.541f, 0f, -0.916f, 0.375f)
                reflectiveQuadToRelative(-0.375f, 0.917f)
                quadToRelative(0f, 0.583f, 0.375f, 0.958f)
                reflectiveQuadTo(11.5f, 18f)
                close()
                moveToRelative(0f, -5.208f)
                horizontalLineToRelative(17.083f)
                quadToRelative(0.5f, 0f, 0.875f, -0.396f)
                reflectiveQuadToRelative(0.375f, -0.938f)
                quadToRelative(0f, -0.541f, -0.395f, -0.937f)
                quadToRelative(-0.396f, -0.396f, -0.896f, -0.396f)
                horizontalLineTo(11.458f)
                quadToRelative(-0.541f, 0f, -0.916f, 0.396f)
                reflectiveQuadToRelative(-0.375f, 0.937f)
                quadToRelative(0f, 0.542f, 0.375f, 0.938f)
                quadToRelative(0.375f, 0.396f, 0.958f, 0.396f)
                close()
                moveTo(3.625f, 33.125f)
                verticalLineTo(6.208f)
                quadToRelative(0f, -1.041f, 0.771f, -1.833f)
                reflectiveQuadToRelative(1.854f, -0.792f)
                horizontalLineToRelative(27.5f)
                quadToRelative(1.042f, 0f, 1.833f, 0.792f)
                quadToRelative(0.792f, 0.792f, 0.792f, 1.833f)
                verticalLineToRelative(20.917f)
                quadToRelative(0f, 1.042f, -0.792f, 1.833f)
                quadToRelative(-0.791f, 0.792f, -1.833f, 0.792f)
                horizontalLineTo(10.125f)
                lineToRelative(-4.292f, 4.292f)
                quadToRelative(-0.625f, 0.625f, -1.416f, 0.27f)
                quadToRelative(-0.792f, -0.354f, -0.792f, -1.187f)
                close()
                moveToRelative(2.625f, -3.25f)
                lineTo(9f, 27.125f)
                horizontalLineToRelative(24.75f)
                verticalLineTo(6.208f)
                horizontalLineTo(6.25f)
                close()
                moveToRelative(0f, -23.667f)
                verticalLineToRelative(23.667f)
                close()
            }
        }.build()
    }
}
