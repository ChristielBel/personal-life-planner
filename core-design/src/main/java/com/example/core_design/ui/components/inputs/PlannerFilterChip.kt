package com.example.core_design.ui.components.inputs

import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Компонент фильтра для выбора одного из вариантов отображения данных.
 *
 * Используется для переключения между категориями, например:
 * «Все», «Сегодня», «Активные» и «Выполненные».
 *
 * Состояние выбора контролируется извне.
 *
 * Пример использования:
 * ```
 * var selectedFilter by remember {
 *     mutableStateOf("all")
 * }
 *
 * PlannerFilterChip(
 *     label = "Все",
 *     selected = selectedFilter == "all",
 *     onClick = { selectedFilter = "all" }
 * )
 *
 * PlannerFilterChip(
 *     label = "Выполненные",
 *     selected = selectedFilter == "completed",
 *     onClick = { selectedFilter = "completed" }
 * )
 * ```
 *
 * @param label текст фильтра.
 * @param selected определяет, выбран ли фильтр.
 * @param onClick действие при нажатии.
 * @param modifier модификатор внешнего оформления.
 * @param enabled определяет, доступен ли фильтр для выбора.
 */
@Composable
fun PlannerFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        shape = MaterialTheme.shapes.medium
    )
}
