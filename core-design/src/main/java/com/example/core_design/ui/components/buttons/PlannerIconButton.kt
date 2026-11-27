package com.example.core_design.ui.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Кнопка приложения, отображающая только иконку.
 *
 * Предназначена для компактных действий: редактирования,
 * удаления, закрытия диалогов и других операций.
 *
 * Пример использования:
 * ```
 * PlannerIconButton(
 *     icon = Icons.Default.Edit,
 *     contentDescription = "Редактировать",
 *     onClick = onEdit
 * )
 *
 * PlannerIconButton(
 *     icon = Icons.Default.Delete,
 *     contentDescription = "Удалить",
 *     onClick = onDelete,
 *     enabled = false
 * )
 * ```
 *
 * @param icon векторная иконка кнопки.
 * @param contentDescription описание назначения иконки для accessibility.
 * @param onClick действие при нажатии.
 * @param modifier модификатор внешнего оформления.
 * @param enabled определяет, доступна ли кнопка для нажатия.
 */
@Composable
fun PlannerIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}