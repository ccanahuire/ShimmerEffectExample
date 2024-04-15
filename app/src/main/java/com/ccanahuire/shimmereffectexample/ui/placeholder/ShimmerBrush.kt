package com.ccanahuire.shimmereffectexample.ui.placeholder

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun shimmerBrush(): Brush {
    val configuration = LocalConfiguration.current
    val targetOffset = with(LocalDensity.current) {
        configuration.screenWidthDp.dp.toPx() * 1.5f
    }

    val baseColor = MaterialTheme.colorScheme.onBackground
    val shimmerColors = remember(baseColor) {
        listOf(
            baseColor.copy(alpha = 0.2f),
            baseColor.copy(alpha = 0.2f),
            Color.White.copy(alpha = 0.4f),
            baseColor.copy(alpha = 0.2f),
            baseColor.copy(alpha = 0.2f)
        )
    }

    val transition = rememberInfiniteTransition("Shimmer infinite transition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(1500), repeatMode = RepeatMode.Restart
        ),
        label = "Shimmer loading animation"
    )
     return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
}