package com.wil8dev.labrocante.view.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Float.toDp(): Dp = with(LocalDensity.current) { this@toDp.toDp() }

@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { this@toPx.toPx() }

@Composable
fun getWidthDp(): Dp = LocalConfiguration.current.screenWidthDp.dp

@Composable
fun getHeightDp(): Dp = LocalConfiguration.current.screenHeightDp.dp

@Composable
fun getWidthPx(): Float = getWidthDp().toPx()

@Composable
fun getHeightPx(): Float = getHeightDp().toPx()

@Composable
fun getMinDimensionDp(): Dp {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp
    return if (width < height) width.dp else height.dp
}

@Composable
fun getMinDimensionPx(): Float {
    with(LocalDensity.current) {
        val configuration = LocalConfiguration.current
        val width = configuration.screenWidthDp.dp.toPx()
        val height = configuration.screenHeightDp.dp.toPx()
        return if (width < height) width else height
    }
}
