package com.example.core_design.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.localization.Strings

/**
 * Диалог подтверждения действия.
 *
 * Используется для операций, которые требуют дополнительного
 * подтверждения пользователя, например удаления задачи,
 * заметки, события или другого элемента.
 *
 * Построен на основе PlannerDialog и автоматически использует
 * локализованные тексты кнопок удаления и отмены по умолчанию.
 *
 * Пример использования:
 * ```
 * PlannerConfirmDialog(
 *     title = "Удалить задачу?",
 *     message = "Задача «Прочитать книгу» будет удалена.",
 *     onConfirm = onDelete,
 *     onDismiss = onDismiss
 * )
 * ```
 *
 * Пример с пользовательскими текстами кнопок:
 * ```
 * PlannerConfirmDialog(
 *     title = "Выйти из приложения?",
 *     message = "Несохранённые изменения могут быть потеряны.",
 *     confirmText = "Выйти",
 *     dismissText = "Остаться",
 *     onConfirm = onExit,
 *     onDismiss = onDismiss
 * )
 * ```
 *
 * @param title заголовок диалога.
 * @param message текст с описанием подтверждаемого действия.
 * @param confirmText текст кнопки подтверждения.
 * @param dismissText текст кнопки отмены.
 * @param onConfirm действие при подтверждении.
 * @param onDismiss действие при отмене или закрытии диалога.
 */
@Composable
fun PlannerConfirmDialog(
    title: String,
    message: String,
    confirmText: String = stringResource(Strings.Common.DELETE),
    dismissText: String = stringResource(Strings.Task.DELETE_CANCEL),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    PlannerDialog(
        title = title,
        description = message,
        confirmText = confirmText,
        dismissText = dismissText,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        content = {}
    )
}