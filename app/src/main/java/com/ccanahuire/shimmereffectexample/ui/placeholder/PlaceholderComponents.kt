package com.ccanahuire.shimmereffectexample.ui.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CirclePlaceholder(
    size: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(shimmerBrush())
    )
}

@Composable
fun TextPlaceholder(
    modifier: Modifier = Modifier,
    textSize: TextUnit = LocalTextStyle.current.fontSize,
    numLines: Int = 1,
    lineSpacing: Dp = 4.dp,
    shape: Shape = MaterialTheme.shapes.extraSmall
) {
    val dimension = LocalDensity.current
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min),
        verticalArrangement = Arrangement.spacedBy(lineSpacing)
    ) {
        repeat(numLines) {
            Box(
                modifier = Modifier
                    .height(
                        with(dimension) { textSize.toDp() }
                    )
                    .background(shimmerBrush(), shape)
                    .fillMaxWidth(if (it == numLines - 1) 0.5f else 1f)
            )
        }
    }
}