
package com.example.personal_life_planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.personal_life_planner.ui.theme.PersonallifeplannerTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

// MARK: - Colors

private val Background = Color(0xFFF7F6F1)
private val SurfaceWhite = Color(0xFFFFFEFA)
private val ForestGreen = Color(0xFF526B55)
private val LightGreen = Color(0xFFE8EEE5)
private val MutedGreen = Color(0xFF879B83)
private val TextPrimary = Color(0xFF30362F)
private val TextSecondary = Color(0xFF898D83)
private val BorderColor = Color(0xFFEAE9E0)
private val SoftOrange = Color(0xFFF3E5D5)
private val SoftPink = Color(0xFFF3E5E2)

// MARK: - Activity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PersonallifeplannerTheme {
                PlannerApp()
            }
        }
    }
}

// MARK: - App

@Composable
fun PlannerApp() {

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            PlannerBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->

        when (selectedTab) {
            0 -> HomeScreen(
                modifier = Modifier.padding(innerPadding)
            )

            else -> PlaceholderScreen(
                title = listOf(
                    "Сегодня",
                    "Планирование",
                    "Цели",
                    "Библиотека"
                )[selectedTab],
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

// MARK: - Home Screen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val today = remember { LocalDate.now() }

    var completedTasks by remember {
        mutableStateOf(setOf<Int>())
    }

    var completedHabits by remember {
        mutableStateOf(setOf<Int>())
    }

    val tasks = listOf(
        "Подготовить план проекта",
        "Заняться английским",
        "Прочитать 20 страниц",
        "Сходить на тренировку"
    )

    val habits = listOf(
        "Выпить 2 литра воды",
        "Утренняя зарядка",
        "Чтение 20 минут"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // Header

        HeaderSection(today)

        // Weekly Calendar

        WeeklyCalendar(today)

        // Daily Tasks

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SectionTitle(
                title = "Главное сегодня",
                subtitle = "Твои приоритеты"
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = SurfaceWhite,
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    BorderColor
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Задачи на день",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )

                        Text(
                            text = "${completedTasks.size}/${tasks.size}",
                            color = ForestGreen,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    ProgressLine(
                        progress = completedTasks.size.toFloat() / tasks.size
                    )

                    tasks.forEachIndexed { index, task ->

                        TaskItem(
                            title = task,
                            checked = index in completedTasks,
                            onCheckedChange = { checked ->
                                completedTasks = if (checked) {
                                    completedTasks + index
                                } else {
                                    completedTasks - index
                                }
                            }
                        )
                    }
                }
            }
        }

        // Habits

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SectionTitle(
                title = "Мои привычки",
                subtitle = "Маленькие шаги каждый день"
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = LightGreen
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Ежедневный прогресс",
                            color = TextPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )

                        Text(
                            text = "${completedHabits.size}/${habits.size}",
                            color = ForestGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    habits.forEachIndexed { index, habit ->

                        HabitItem(
                            title = habit,
                            checked = index in completedHabits,
                            onCheckedChange = { checked ->
                                completedHabits = if (checked) {
                                    completedHabits + index
                                } else {
                                    completedHabits - index
                                }
                            }
                        )
                    }
                }
            }
        }

        // Upcoming Event

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SectionTitle(
                title = "Ближайшее событие",
                subtitle = "Не забудь о важном"
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = SoftOrange
            ) {

                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "24",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Text(
                            text = "НОЯ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.width(18.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Text(
                            text = "Встреча",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )

                        Text(
                            text = "Обсуждение планов · 14:00",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        // Daily Note

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SectionTitle(
                title = "Мысль дня",
                subtitle = "Запиши то, что важно"
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = SoftPink
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "✎  Маленькая заметка",
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary,
                        fontSize = 15.sp
                    )

                    Text(
                        text = "Каждый большой результат начинается с маленького шага.",
                        color = TextSecondary,
                        fontSize = 14.sp,
                        lineHeight = 21.sp
                    )
                }
            }
        }
    }
}

// MARK: - Header

@Composable
private fun HeaderSection(today: LocalDate) {

    val date = today.format(
        DateTimeFormatter.ofPattern(
            "EEEE, d MMMM",
            Locale("ru")
        )
    ).replaceFirstChar {
        it.titlecase(Locale("ru"))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = date.uppercase(Locale("ru")),
                color = TextSecondary,
                fontSize = 11.sp,
                letterSpacing = 1.sp
            )

            Text(
                text = "Добрый день!",
                color = TextPrimary,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Давай сделаем этот день продуктивным",
                color = TextSecondary,
                fontSize = 13.sp
            )
        }

        Surface(
            shape = CircleShape,
            color = LightGreen,
            modifier = Modifier.size(52.dp)
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "M",
                    color = ForestGreen,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// MARK: - Weekly Calendar

@Composable
private fun WeeklyCalendar(today: LocalDate) {

    val monday = today.with(DayOfWeek.MONDAY)

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        SectionTitle(
            title = "Эта неделя",
            subtitle = "${today.month.getDisplayName(TextStyle.FULL, Locale("ru"))
                .replaceFirstChar { it.titlecase(Locale("ru")) }} ${today.year}"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            (0..6).forEach { index ->

                val date = monday.plusDays(index.toLong())
                val isToday = date == today

                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = if (isToday) ForestGreen else SurfaceWhite,
                    border = if (isToday) null else
                        androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
                ) {

                    Column(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = date.dayOfWeek
                                .getDisplayName(TextStyle.SHORT, Locale("ru"))
                                .take(2)
                                .uppercase(Locale("ru")),
                            fontSize = 10.sp,
                            color = if (isToday) Color.White.copy(alpha = 0.8f)
                            else TextSecondary
                        )

                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isToday) Color.White else TextPrimary
                        )

                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(
                                    if (isToday) Color.White else Color.Transparent,
                                    CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}

// MARK: - Section Title

@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = title,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Text(
            text = subtitle,
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}

// MARK: - Task Item

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
                checkedColor = ForestGreen,
                uncheckedColor = MutedGreen
            )
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            color = if (checked) TextSecondary else TextPrimary,
            fontWeight = if (checked) FontWeight.Normal
            else FontWeight.Medium
        )

        Text(
            text = if (checked) "Готово" else " ",
            fontSize = 11.sp,
            color = ForestGreen
        )
    }
}

// MARK: - Habit Item

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
                checkedColor = ForestGreen,
                uncheckedColor = MutedGreen
            )
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            color = TextPrimary
        )

        Text(
            text = if (checked) "✓" else "○",
            fontSize = 17.sp,
            color = ForestGreen
        )
    }
}

// MARK: - Progress

@Composable
private fun ProgressLine(progress: Float) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(7.dp)
            .clip(CircleShape)
            .background(LightGreen)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .fillMaxHeight()
                .clip(CircleShape)
                .background(ForestGreen)
        )
    }
}

// MARK: - Bottom Navigation

@Composable
private fun PlannerBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {

    val items = listOf(
        Triple("⌂", "Сегодня", 0),
        Triple("▦", "План", 1),
        Triple("◎", "Цели", 2),
        Triple("⋯", "Ещё", 3)
    )

    NavigationBar(
        containerColor = SurfaceWhite,
        tonalElevation = 0.dp
    ) {

        items.forEach { (icon, label, index) ->

            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Text(
                        text = icon,
                        fontSize = 21.sp
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 11.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ForestGreen,
                    selectedTextColor = ForestGreen,
                    indicatorColor = LightGreen,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary
                )
            )
        }
    }
}

// MARK: - Temporary Placeholder

@Composable
private fun PlaceholderScreen(
    title: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = title,
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Раздел скоро появится",
                color = TextSecondary,
                fontSize = 14.sp
            )
        }
    }
}

// MARK: - Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {

    PersonallifeplannerTheme {
        HomeScreen()
    }
}
