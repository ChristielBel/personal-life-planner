package com.example.core_design.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core_design.ui.theme.Spacing

/**
 * Универсальная кнопка.
 *
 * Поддерживает несколько визуальных стилей: основная, вторичная,
 * контурная, текстовая и destructive-кнопка.
 *
 * Может отображать дополнительный контент перед текстом,
 * например иконку.
 *
 * Пример использования:
 * ```
 * PlannerButton(
 *     text = "Сохранить",
 *     onClick = onSave
 * )
 *
 * PlannerButton(
 *     text = "Удалить",
 *     onClick = onDelete,
 *     style = PlannerButtonStyle.DESTRUCTIVE
 * )
 *
 * PlannerButton(
 *     text = "Отмена",
 *     onClick = onCancel,
 *     style = PlannerButtonStyle.OUTLINED
 * )
 * ```
 *
 * @param text текст кнопки.
 * @param onClick действие при нажатии.
 * @param modifier модификатор внешнего оформления.
 * @param enabled определяет, доступна ли кнопка для нажатия.
 * @param style визуальный стиль кнопки.
 * @param leadingContent необязательный контент перед текстом кнопки.
 */
enum class PlannerButtonStyle {
    PRIMARY,
    SECONDARY,
    OUTLINED,
    TEXT,
    DESTRUCTIVE
}

@Composable
fun PlannerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: PlannerButtonStyle = PlannerButtonStyle.PRIMARY,
    leadingContent: (@Composable () -> Unit)? = null
) {
    val content: @Composable () -> Unit = {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
        ) {
            leadingContent?.invoke()
            Text(text = text)
        }
    }

    when (style) {
        PlannerButtonStyle.PRIMARY -> {
            Button(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                content = { content() }
            )
        }

        PlannerButtonStyle.SECONDARY -> {
            FilledTonalButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                content = { content() }
            )
        }

        PlannerButtonStyle.OUTLINED -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                content = { content() }
            )
        }

        PlannerButtonStyle.TEXT -> {
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                content = { content() }
            )
        }

        PlannerButtonStyle.DESTRUCTIVE -> {
            Button(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                content = { content() }
            )
        }
    }
}