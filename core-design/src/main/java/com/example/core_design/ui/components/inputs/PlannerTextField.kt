package com.example.core_design.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Универсальное текстовое поле приложения.
 *
 * Поддерживает подпись, placeholder, вспомогательный текст,
 * состояние ошибки и дополнительные иконки.
 *
 * Используется для ввода названий задач, заметок, целей
 * и другой пользовательской информации.
 *
 * Пример использования:
 * ```
 * var title by remember { mutableStateOf("") }
 *
 * PlannerTextField(
 *     value = title,
 *     onValueChange = { title = it },
 *     label = "Название задачи",
 *     placeholder = "Например, прочитать книгу"
 * )
 *
 * PlannerTextField(
 *     value = title,
 *     onValueChange = { title = it },
 *     label = "Название",
 *     isError = title.isBlank(),
 *     supportingText = "Название не должно быть пустым"
 * )
 * ```
 *
 * @param value текущее значение текстового поля.
 * @param onValueChange вызывается при изменении текста.
 * @param modifier модификатор внешнего оформления.
 * @param label подпись текстового поля.
 * @param placeholder подсказка внутри пустого поля.
 * @param enabled определяет, доступно ли поле для редактирования.
 * @param singleLine определяет, разрешён ли ввод в одну строку.
 * @param isError определяет, отображается ли поле в состоянии ошибки.
 * @param supportingText дополнительный текст под полем.
 * @param leadingIcon контент перед текстом.
 * @param trailingIcon контент после текста.
 */
@Composable
fun PlannerTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = singleLine,
        isError = isError,
        shape = MaterialTheme.shapes.medium,
        label = label?.let {
            { Text(it) }
        },
        placeholder = placeholder?.let {
            { Text(it) }
        },
        supportingText = supportingText?.let {
            { Text(it) }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}
