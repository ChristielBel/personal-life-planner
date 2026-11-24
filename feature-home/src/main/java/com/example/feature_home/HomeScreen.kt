package com.example.feature_home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core_design.ui.components.PlannerActionTile
import com.example.core_design.ui.components.PlannerCard
import com.example.core_design.ui.components.PlannerProgressBar
import com.example.core_design.ui.components.SectionHeader
import com.example.core_design.ui.theme.LightSage
import com.example.core_design.ui.theme.PersonallifeplannerTheme
import com.example.core_design.ui.theme.SoftOrange
import com.example.core_design.ui.theme.SoftPink
import com.example.core_design.ui.theme.Spacing
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.format.TextStyle as DateTextStyle

private val russianLocale = Locale.forLanguageTag("ru")

// ---------------------------------------------------------
// HOME SCREEN
// ---------------------------------------------------------

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    HomeContent(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier
    )
}

// ---------------------------------------------------------
// HOME CONTENT
// ---------------------------------------------------------

@Composable
private fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedTasks = state.tasks.count { it.isCompleted }
    val completedHabits = state.habits.count { it.isCompleted }

    val taskProgress = if (state.tasks.isEmpty()) {
        0f
    } else {
        completedTasks.toFloat() / state.tasks.size
    }

    val habitProgress = if (state.habits.isEmpty()) {
        0f
    } else {
        completedHabits.toFloat() / state.habits.size
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Spacing.xl)
            .padding(
                top = Spacing.xl,
                bottom = Spacing.xxxl
            ),
        verticalArrangement = Arrangement.spacedBy(Spacing.xl)
    ) {

        // 1. Шапка

        HeaderSection(
            today = state.date,
            greeting = state.greeting
        )

        // 2. Календарь недели

        WeeklyCalendar(
            today = state.date
        )

        // 3. Главные приоритеты

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Главное сегодня",
                subtitle = "Три важных дела на сегодняшний день"
            )

            PlannerCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Мои приоритеты",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "${completedTasks}/${state.tasks.size}",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                PlannerProgressBar(taskProgress)

                if (state.tasks.isEmpty()) {
                    Text(
                        text = "Пока нет задач",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                state.tasks.forEach { task ->
                    TaskItem(
                        title = task.title,
                        checked = task.isCompleted,
                        onCheckedChange = {
                            onEvent(HomeEvent.ToggleTask(task.id))
                        }
                    )
                }
            }
        }

        // 4. Расписание

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Расписание",
                subtitle = "Твои планы и события на сегодня"
            )

            PlannerCard {
                if (state.events.isEmpty()) {
                    Text(
                        text = "На сегодня событий нет",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                state.events.forEachIndexed { index, event ->
                    if (index > 0) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outline
                        )
                    }

                    ScheduleItem(
                        time = event.time,
                        title = event.title,
                        subtitle = event.description,
                        accent = if (index % 2 == 0) {
                            LightSage
                        } else {
                            SoftOrange
                        }
                    )
                }

                TextButton(
                    onClick = {
                        onEvent(HomeEvent.AddEvent)
                    }
                ) {
                    Text("+ Добавить событие")
                }
            }
        }

        // 5. Привычки

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Мои привычки",
                subtitle = "Маленькие шаги каждый день"
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                color = LightSage
            ) {
                Column(
                    modifier = Modifier.padding(Spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Spacing.md)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Ежедневный прогресс",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "$completedHabits/${state.habits.size}",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    PlannerProgressBar(habitProgress)

                    if (state.habits.isEmpty()) {
                        Text(
                            text = "Пока нет привычек",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    state.habits.forEach { habit ->
                        HabitItem(
                            title = habit.title,
                            checked = habit.isCompleted,
                            onCheckedChange = {
                                onEvent(HomeEvent.ToggleHabit(habit.id))
                            }
                        )
                    }
                }
            }
        }

        // 6. Активная цель

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Мои цели",
                subtitle = "Не забывай о том, к чему стремишься"
            )

            state.activeGoal?.let { goal ->
                PlannerCard {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = goal.title,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "${(goal.progress * 100).toInt()}%",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    PlannerProgressBar(goal.progress)

                    Text(
                        text = "Следующий шаг: ${goal.nextStep}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } ?: run {
                PlannerCard {
                    Text(
                        text = "Активная цель пока не задана",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // 7. Финансы

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Финансы",
                subtitle = "Краткий обзор бюджета"
            )

            PlannerCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Spacing.xs)
                    ) {
                        Text(
                            text = "Остаток бюджета",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = state.monthlyBudget?.let {
                                "%.2f €".format(russianLocale, it)
                            } ?: "Не задан",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "€",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = "Демонстрационные данные",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // 8. Заметка дня

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Мысль дня",
                subtitle = "Место для твоих идей и записей"
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                color = SoftPink
            ) {
                Column(
                    modifier = Modifier.padding(Spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Spacing.sm)
                ) {
                    Text(
                        text = "✎  Моя заметка",
                        style = MaterialTheme.typography.titleMedium
                    )

                    OutlinedTextField(
                        value = state.dailyNote,
                        onValueChange = {
                            onEvent(HomeEvent.UpdateNote(it))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Запиши свою мысль...")
                        },
                        minLines = 2,
                        maxLines = 4,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )
                }
            }
        }

        // 9. Быстрое добавление

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            SectionHeader(
                title = "Быстрое добавление",
                subtitle = "Записывай важное в один клик"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
            ) {
                PlannerActionTile(
                    symbol = "✓",
                    label = "Задача",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEvent(HomeEvent.AddTask)
                    }
                )

                PlannerActionTile(
                    symbol = "▦",
                    label = "Событие",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEvent(HomeEvent.AddEvent)
                    }
                )

                PlannerActionTile(
                    symbol = "✎",
                    label = "Заметка",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEvent(HomeEvent.AddNote)
                    }
                )

                PlannerActionTile(
                    symbol = "€",
                    label = "Расход",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEvent(HomeEvent.AddExpense)
                    }
                )
            }
        }
    }
}

// ---------------------------------------------------------
// HEADER
// ---------------------------------------------------------

@Composable
private fun HeaderSection(
    today: LocalDate,
    greeting: String
) {
    val date = today.format(
        DateTimeFormatter.ofPattern(
            "EEEE, d MMMM",
            russianLocale
        )
    ).replaceFirstChar {
        it.titlecase(russianLocale)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Text(
                text = date.uppercase(russianLocale),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = greeting,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Давай сделаем этот день продуктивным",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Surface(
            shape = CircleShape,
            color = LightSage,
            modifier = Modifier.size(52.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "M",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// ---------------------------------------------------------
// WEEKLY CALENDAR
// ---------------------------------------------------------

@Composable
private fun WeeklyCalendar(today: LocalDate) {
    val monday = today.with(DayOfWeek.MONDAY)

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        SectionHeader(
            title = "Эта неделя",
            subtitle = "${today.month.getDisplayName(DateTextStyle.FULL, russianLocale)
                .replaceFirstChar { it.titlecase(russianLocale) }} ${today.year}"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            (0..6).forEach { index ->
                val date = monday.plusDays(index.toLong())
                val isToday = date == today

                Surface(
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    color = if (isToday) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.surface
                    },
                    border = if (isToday) {
                        null
                    } else {
                        BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outline
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = Spacing.md),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Spacing.sm)
                    ) {
                        Text(
                            text = date.dayOfWeek
                                .getDisplayName(DateTextStyle.SHORT, russianLocale)
                                .take(2)
                                .uppercase(russianLocale),
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isToday) {
                                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )

                        Text(
                            text = date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isToday) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        )

                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(
                                    color = if (isToday) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        Color.Transparent
                                    },
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------
// TASK ITEM
// ---------------------------------------------------------

@Composable
private fun TaskItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.secondary
            )
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = if (checked) {
                MaterialTheme.colorScheme.onSurfaceVariant
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            fontWeight = if (checked) {
                FontWeight.Normal
            } else {
                FontWeight.Medium
            }
        )

        Text(
            text = if (checked) "Готово" else "",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// ---------------------------------------------------------
// HABIT ITEM
// ---------------------------------------------------------

@Composable
private fun HabitItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.secondary
            )
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = if (checked) "✓" else "○",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// ---------------------------------------------------------
// SCHEDULE ITEM
// ---------------------------------------------------------

@Composable
private fun ScheduleItem(
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
                .width(4.dp)
                .height(42.dp)
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

// ---------------------------------------------------------
// PREVIEW
// ---------------------------------------------------------

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    PersonallifeplannerTheme {
        HomeContent(
            state = HomeState(
                tasks = listOf(
                    HomeTask(1, "Подготовить план проекта", false),
                    HomeTask(2, "Заняться английским", true),
                    HomeTask(3, "Прочитать 20 страниц", false)
                ),
                events = listOf(
                    HomeCalendarEvent(
                        1,
                        "09:00",
                        "Английский",
                        "Обучение · 45 минут"
                    ),
                    HomeCalendarEvent(
                        2,
                        "14:00",
                        "Встреча",
                        "Обсуждение планов"
                    )
                ),
                habits = listOf(
                    HomeHabit(1, "Выпить 2 литра воды", false),
                    HomeHabit(2, "Утренняя зарядка", true),
                    HomeHabit(3, "Чтение 20 минут", false)
                ),
                activeGoal = HomeGoal(
                    "Английский до уровня B2",
                    0.45f,
                    "Пройти один урок"
                ),
                monthlyBudget = 850.0,
                dailyNote = "Каждый большой результат начинается с маленького шага."
            ),
            onEvent = {}
        )
    }
}
