package com.example.core_design.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import com.example.core_design.ui.theme.Spacing

/**
 * Компонент пустого состояния.
 *
 * Отображает заголовок, необязательное описание, иконку
 * и дополнительное действие.
 *
 * Используется, когда список задач, заметок, событий
 * или других элементов пока не содержит данных.
 *
 * Пример использования:
 * ```
 * PlannerEmptyState(
 *     title = "Пока нет задач",
 *     description = "Добавьте первую задачу на сегодня",
 *     icon = Icons.Default.CheckCircle,
 *     action = {
 *         PlannerButton(
 *             text = "Добавить задачу",
 *             onClick = onAddTask
 *         )
 *     }
 * )
 * ```
 *
 * @param title основной текст пустого состояния.
 * @param modifier модификатор внешнего оформления.
 * @param description необязательное пояснение.
 * @param icon необязательная иконка.
 * @param action необязательный слот дополнительного действия.
 */
@Composable
fun PlannerEmptyState(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    icon: ImageVector? = null,
    action: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        action?.invoke()
    }
}