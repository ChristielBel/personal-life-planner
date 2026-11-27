package com.example.core_design.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core_design.ui.theme.Spacing

/**
 * Универсальная карточка приложения с вертикальным расположением контента.
 *
 * Используется как контейнер для группировки связанных элементов:
 * статистики, задач, привычек, заметок и других блоков интерфейса.
 *
 * Внутренние элементы располагаются вертикально с единым
 * расстоянием между ними.
 *
 * Пример использования:
 * ```
 * PlannerCard {
 *     Text(
 *         text = "Мои задачи",
 *         style = MaterialTheme.typography.titleLarge
 *     )
 *
 *     Text(
 *         text = "Сегодня необходимо выполнить 3 задачи"
 *     )
 *
 *     PlannerProgressBar(progress = 0.6f)
 * }
 * ```
 *
 * @param modifier модификатор внешнего оформления карточки.
 * @param content содержимое карточки, доступное в контексте ColumnScope.
 */
@Composable
fun PlannerCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier.padding(Spacing.lg),
            verticalArrangement = Arrangement.spacedBy(Spacing.md),
            content = content
        )
    }
}
