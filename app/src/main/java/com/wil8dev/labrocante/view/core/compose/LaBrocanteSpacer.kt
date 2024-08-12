package com.wil8dev.labrocante.view.core.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wil8dev.labrocante.view.core.compose.LaBrocanteSpace.MEDIUM

@Composable
fun ColumnScope.LaBrocanteSpacer(
    modifier: Modifier = Modifier,
    height: LaBrocanteSpace = MEDIUM,
) {
    Spacer(modifier = modifier.height(height = height.value))
}

@Composable
fun RowScope.LaBrocanteSpacer(
    modifier: Modifier = Modifier,
    width: LaBrocanteSpace = MEDIUM,
) {
    Spacer(modifier = modifier.width(width = width.value))
}

@Composable
fun ColumnScope.LaBrocanteWeighthSpacer(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
) {
    Spacer(modifier = modifier.weight(weight = weight))
}

@Composable
fun RowScope.LaBrocanteWeighthSpacer(
    modifier: Modifier = Modifier,
    weight: Float = 1f,
) {
    Spacer(modifier = modifier.weight(weight = weight))
}

@Suppress("unused")
enum class LaBrocanteSpace(
    val value: Dp,
) {

    XX_SMALL(2.dp),
    X_SMALL(4.dp),
    SMALL(8.dp),
    MEDIUM(16.dp),
    LARGE(24.dp),
    X_LARGE(32.dp),
    XX_LARGE(40.dp),
    XXX_LARGE(80.dp),
    XXXX_LARGE(160.dp),
}
