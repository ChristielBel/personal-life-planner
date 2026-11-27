package com.example.core_design.ui.components.indicators

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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

/**
 * Анимированный индикатор выполнения задачи или другого процесса.
 *
 * Отображает заполненную часть горизонтальной полосы
 * в соответствии со значением прогресса.
 *
 * При изменении progress ширина заполненной части
 * плавно анимируется до нового значения.
 *
 * Значение автоматически ограничивается диапазоном от 0 до 1.
 *
 * Пример использования:
 * ```
 * PlannerProgressBar(
 *     progress = 0.75f
 * )
 *
 * PlannerProgressBar(
 *     progress = completedTasks.toFloat() / totalTasks,
 *     height = 10.dp,
 *     animationDuration = 600
 * )
 * ```
 *
 * Перед вычислением прогресса необходимо учитывать возможность
 * деления на ноль, если общее количество элементов равно нулю.
 *
 * @param progress значение прогресса от 0f до 1f.
 * @param modifier модификатор внешнего оформления.
 * @param height высота индикатора.
 * @param trackColor цвет незаполненной части индикатора.
 * @param progressColor цвет заполненной части индикатора.
 * @param animationDuration длительность анимации в миллисекундах.
 */
@Composable
fun PlannerProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 7.dp,
    trackColor: Color = LightSage,
    progressColor: Color = ForestGreen,
    animationDuration: Int = 500
) {
    val safeProgress = progress.coerceIn(0f, 1f)

    val animatedProgress = animateFloatAsState(
        targetValue = safeProgress,
        animationSpec = tween(
            durationMillis = animationDuration
        ),
        label = "PlannerProgressAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(trackColor)
    ) {
        if (animatedProgress.value > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress.value)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(progressColor)
            )
        }
    }
}
