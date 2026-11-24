
package com.example.core_design.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core_design.ui.theme.ForestGreen
import com.example.core_design.ui.theme.LightSage

@Composable
fun PlannerProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 7.dp,
    trackColor: Color = LightSage,
    progressColor: Color = ForestGreen
) {
    val safeProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(trackColor)
    ) {
        if (safeProgress > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(safeProgress)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(progressColor)
            )
        }
    }
}
