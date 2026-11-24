package com.example.feature_home

import java.time.LocalDate

data class HomeState(
    val date: LocalDate = LocalDate.now(),
    val greeting: String = "Добрый день!",
    val tasks: List<HomeTask> = emptyList(),
    val events: List<HomeCalendarEvent> = emptyList(),
    val habits: List<HomeHabit> = emptyList(),
    val activeGoal: HomeGoal? = null,
    val monthlyBudget: Double? = null,
    val dailyNote: String = "",
    val isLoading: Boolean = false
)

data class HomeTask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class HomeCalendarEvent(
    val id: Int,
    val time: String,
    val title: String,
    val description: String
)

data class HomeHabit(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class HomeGoal(
    val title: String,
    val progress: Float,
    val nextStep: String
)
