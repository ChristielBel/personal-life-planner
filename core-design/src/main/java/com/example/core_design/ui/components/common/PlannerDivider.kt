package com.example.core_design.ui.components.common

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Горизонтальный разделитель в стиле приложения.
 *
 * Используется для визуального разделения элементов интерфейса,
 * например строк списка или секций карточки.
 *
 * Пример использования:
 * ```
 * PlannerDivider()
 *
 * PlannerDivider(
 *     modifier = Modifier.padding(vertical = 8.dp)
 * )
 * ```
 *
 * @param modifier модификатор внешнего оформления.
 */
@Composable
fun PlannerDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.7f)
    )
}
