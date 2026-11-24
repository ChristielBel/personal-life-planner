package com.example.feature_home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeState(
            tasks = listOf(
                HomeTask(1, "Подготовить план проекта", false),
                HomeTask(2, "Заняться английским", false),
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
                HomeHabit(2, "Утренняя зарядка", false),
                HomeHabit(3, "Чтение 20 минут", false)
            ),
            activeGoal = HomeGoal(
                "Английский до уровня B2",
                0.45f,
                "Пройти один урок"
            ),
            monthlyBudget = 850.0,
            dailyNote = "Каждый большой результат начинается с маленького шага."
        )
    )

    val uiState: StateFlow<HomeState> =
        _uiState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ToggleTask -> {
                _uiState.update { state ->
                    state.copy(
                        tasks = state.tasks.map { task ->
                            if (task.id == event.id) {
                                task.copy(
                                    isCompleted = !task.isCompleted
                                )
                            } else {
                                task
                            }
                        }
                    )
                }
            }

            is HomeEvent.ToggleHabit -> {
                _uiState.update { state ->
                    state.copy(
                        habits = state.habits.map { habit ->
                            if (habit.id == event.id) {
                                habit.copy(
                                    isCompleted = !habit.isCompleted
                                )
                            } else {
                                habit
                            }
                        }
                    )
                }
            }

            is HomeEvent.UpdateNote -> {
                _uiState.update {
                    it.copy(dailyNote = event.value)
                }
            }

            HomeEvent.AddTask -> Unit
            HomeEvent.AddEvent -> Unit
            HomeEvent.AddNote -> Unit
            HomeEvent.AddExpense -> Unit
        }
    }
}
