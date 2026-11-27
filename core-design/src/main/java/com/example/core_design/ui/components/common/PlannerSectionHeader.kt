package com.example.core_design.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.core_design.ui.theme.Spacing

/**
 * Заголовок секции с основным текстом и подзаголовком.
 *
 * Используется для визуального разделения смысловых блоков
 * на экранах приложения.
 *
 * Пример использования:
 * ```
 * SectionHeader(
 *     title = "Мои приоритеты",
 *     subtitle = "Самое важное на сегодня"
 * )
 * ```
 *
 * @param title основной заголовок секции.
 * @param subtitle дополнительное описание секции.
 * @param modifier модификатор внешнего оформления.
 */
@Composable
fun PlannerSectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        subtitle?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
