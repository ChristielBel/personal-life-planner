package com.example.core_design.ui.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Компактная метка для отображения короткого статуса или категории.
 *
 * Используется для обозначения приоритета, состояния задачи,
 * категории или другой дополнительной информации.
 *
 * Пример использования:
 * ```
 * PlannerBadge(
 *     text = "Важно"
 * )
 *
 * PlannerBadge(
 *     text = "Выполнено",
 *     containerColor = MaterialTheme.colorScheme.primaryContainer,
 *     contentColor = MaterialTheme.colorScheme.onPrimaryContainer
 * )
 * ```
 *
 * @param text текст метки.
 * @param modifier модификатор внешнего оформления.
 * @param containerColor цвет фона метки.
 * @param contentColor цвет текста метки.
 */
@Composable
fun PlannerBadge(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {
    Row(
        modifier = modifier
            .background(
                color = containerColor,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor
        )
    }
}