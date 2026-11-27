package com.example.core_design.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core_design.ui.theme.Spacing

/**
 * Карточка для отображения одного статистического показателя.
 *
 * Подходит для отображения количества задач, процента выполнения,
 * числа привычек, расходов и других агрегированных значений.
 *
 * Поддерживает дополнительный контент перед заголовком
 * и после него.
 *
 * Пример использования:
 * ```
 * PlannerStatCard(
 *     title = "Выполнено задач",
 *     value = "12 / 15",
 *     description = "80% от общего количества"
 * )
 * ```
 *
 * @param title название статистического показателя.
 * @param value основное значение показателя.
 * @param modifier модификатор внешнего оформления карточки.
 * @param description необязательное пояснение к значению.
 * @param leadingContent дополнительный контент перед заголовком.
 * @param trailingContent дополнительный контент после заголовка.
 */
@Composable
fun PlannerStatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            Spacing.xxs,
            MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.lg),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
            ) {
                if (leadingContent != null) {
                    leadingContent()
                }

                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                trailingContent?.invoke()
            }

            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

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
