package com.example.core_design.ui.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core_design.ui.theme.Spacing

/**
 * Элемент списка напоминаний с переключателем активности.
 *
 * Отображает название напоминания, время, необязательное описание
 * и переключатель включения или отключения.
 *
 * Компонент не выполняет планирование уведомлений самостоятельно.
 * Он только отображает состояние и сообщает о его изменении.
 *
 * Пример использования:
 * ```
 * PlannerReminderRow(
 *     title = "Утренняя зарядка",
 *     timeLabel = "08:00",
 *     description = "Каждый день",
 *     isEnabled = true,
 *     onEnabledChange = { isEnabled = it }
 * )
 * ```
 *
 * @param title название напоминания.
 * @param timeLabel текстовое представление времени.
 * @param isEnabled определяет, включено ли напоминание.
 * @param onEnabledChange вызывается при изменении состояния переключателя.
 * @param modifier модификатор внешнего оформления.
 * @param description необязательное описание напоминания.
 * @param enabled определяет, доступен ли переключатель.
 */
@Composable
fun PlannerReminderRow(
    title: String,
    timeLabel: String,
    isEnabled: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Spacing.xxs)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = timeLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                if (description != null) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Switch(
                checked = isEnabled,
                onCheckedChange = onEnabledChange,
                enabled = enabled
            )
        }
    }
}
