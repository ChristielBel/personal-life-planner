package com.example.core_design.ui.components.inputs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Компонент выбора даты на основе Material 3 DatePicker.
 *
 * При нажатии открывает диалог с календарём.
 * После подтверждения выбранная дата передаётся наружу
 * в виде количества миллисекунд с начала Unix epoch.
 *
 * Компонент не отвечает за форматирование даты:
 * отображаемую подпись можно передать через selectedDateLabel.
 *
 * Пример использования:
 * ```
 * var selectedDate by remember {
 *     mutableStateOf<Long?>(null)
 * }
 *
 * PlannerDatePicker(
 *     selectedDateMillis = selectedDate,
 *     onDateSelected = { selectedDate = it },
 *     label = "Выбрать дату",
 *     selectedDateLabel = "27.11.2026"
 * )
 * ```
 *
 * @param selectedDateMillis выбранная дата в миллисекундах Unix epoch.
 * @param onDateSelected вызывается после подтверждения выбранной даты.
 * @param label подпись компонента и заголовок календаря.
 * @param modifier модификатор внешнего оформления.
 * @param selectedDateLabel текст кнопки при наличии выбранной даты.
 * @param confirmText текст кнопки подтверждения.
 * @param dismissText текст кнопки отмены.
 * @param enabled определяет, доступен ли выбор даты.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerDatePicker(
    selectedDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    selectedDateLabel: String? = null,
    confirmText: String = "Выбрать",
    dismissText: String = "Отмена",
    enabled: Boolean = true
) {
    var isDialogVisible by remember {
        mutableStateOf(false)
    }

    Button(
        onClick = { isDialogVisible = true },
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = selectedDateLabel ?: label)
    }

    if (isDialogVisible) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDateMillis
        )

        DatePickerDialog(
            onDismissRequest = {
                isDialogVisible = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let(onDateSelected)
                        isDialogVisible = false
                    },
                    enabled = datePickerState.selectedDateMillis != null
                ) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isDialogVisible = false
                    }
                ) {
                    Text(dismissText)
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        text = label,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            )
        }
    }
}
