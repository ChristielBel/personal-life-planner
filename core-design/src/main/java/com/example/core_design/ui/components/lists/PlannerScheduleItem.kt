package com.example.core_design.ui.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.core_design.ui.theme.Spacing

/**
 * Элемент расписания с временем, названием и описанием события.
 *
 * Отображает время события, цветной вертикальный индикатор
 * и текстовую информацию о событии.
 *
 * Используется для представления отдельных событий в расписании,
 * например встреч, занятий или запланированных дел.
 *
 * Пример использования:
 * ```
 * PlannerScheduleItem(
 *     time = "09:00",
 *     title = "Английский",
 *     subtitle = "Обучение · 45 минут",
 *     accent = LightSage
 * )
 *
 * PlannerScheduleItem(
 *     time = "14:00",
 *     title = "Рабочая встреча",
 *     subtitle = "Обсуждение планов",
 *     accent = SoftOrange
 * )
 * ```
 *
 * @param time время начала события.
 * @param title название события.
 * @param subtitle дополнительное описание события.
 * @param accent цвет вертикального индикатора события.
 */
@Composable
fun PlannerScheduleItem(
    time: String,
    title: String,
    subtitle: String,
    accent: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .width(Spacing.xs)
                .height(Spacing.xxxxl)
                .clip(CircleShape)
                .background(accent)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
