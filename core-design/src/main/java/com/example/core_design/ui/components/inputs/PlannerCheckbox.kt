package com.example.core_design.ui.components.inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core_design.ui.theme.Spacing

/**
 * Универсальный флажок приложения с названием и необязательным описанием.
 *
 * Используется для отметки выполненных задач, привычек,
 * целей и других элементов с состоянием true/false.
 *
 * Компонент не хранит состояние самостоятельно:
 * текущее значение и обработчик изменения передаются извне.
 *
 * Пример использования:
 * ```
 * var isCompleted by remember { mutableStateOf(false) }
 *
 * PlannerCheckbox(
 *     checked = isCompleted,
 *     onCheckedChange = { isCompleted = it },
 *     label = "Утренняя зарядка",
 *     description = "Выполнять каждый день"
 * )
 * ```
 *
 * @param checked текущее состояние флажка.
 * @param onCheckedChange вызывается при изменении состояния.
 * @param label основной текст рядом с флажком.
 * @param modifier модификатор внешнего оформления.
 * @param description необязательное пояснение.
 * @param enabled определяет, доступен ли флажок для изменения.
 */
@Composable
fun PlannerCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String? = null,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.xxs)
        ) {
            label?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
