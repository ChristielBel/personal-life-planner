package com.example.core_design.ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.core_design.ui.theme.Spacing
import com.example.localization.Strings

/**
 * Универсальный диалог приложения Personal Life Planner.
 *
 * Поддерживает заголовок, необязательное описание, иконку,
 * произвольное содержимое и кнопки подтверждения и отмены.
 *
 * Подходит для создания, редактирования и подтверждения действий.
 *
 * Если onConfirm равен null, кнопка подтверждения не отображается.
 * Состояние доступности кнопки определяется параметром confirmEnabled.
 *
 * Пример использования:
 * ```
 * PlannerDialog(
 *     title = "Новая задача",
 *     description = "Запиши то, что важно сделать.",
 *     confirmText = "Сохранить",
 *     dismissText = "Отмена",
 *     confirmEnabled = taskTitle.isNotBlank(),
 *     onConfirm = onSave,
 *     onDismiss = onDismiss,
 *     content = {
 *         PlannerTextField(
 *             value = taskTitle,
 *             onValueChange = onTaskTitleChange,
 *             label = "Название задачи"
 *         )
 *     }
 * )
 * ```
 *
 * Пример информационного диалога без кнопки подтверждения:
 * ```
 * PlannerDialog(
 *     title = "Информация",
 *     description = "Изменения сохранены.",
 *     onDismiss = onDismiss,
 *     content = {}
 * )
 * ```
 *
 * @param title заголовок диалога.
 * @param onDismiss действие при закрытии диалога.
 * @param modifier модификатор внешнего оформления.
 * @param description необязательное описание под заголовком.
 * @param icon необязательная иконка в заголовке.
 * @param confirmText текст кнопки подтверждения.
 * @param dismissText текст кнопки отмены.
 * @param confirmEnabled определяет, доступна ли кнопка подтверждения.
 * @param onConfirm необязательное действие при подтверждении.
 * @param content произвольное содержимое диалога.
 */
@Composable
fun PlannerDialog(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    icon: ImageVector? = null,
    confirmText: String = stringResource(Strings.Common.SAVE),
    dismissText: String = stringResource(Strings.Common.CANCEL),
    confirmEnabled: Boolean = true,
    onConfirm: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        icon = icon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.md)
            ) {
                description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                content()
            }
        },
        confirmButton = {
            if (onConfirm != null) {
                Button(
                    onClick = onConfirm,
                    enabled = confirmEnabled
                ) {
                    Text(confirmText)
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(dismissText)
            }
        },
        shape = MaterialTheme.shapes.extraLarge,
        containerColor = MaterialTheme.colorScheme.surface
    )
}
