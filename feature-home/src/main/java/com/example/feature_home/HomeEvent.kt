package com.example.feature_home

sealed class HomeEvent {
    class ToggleTask(val id: Int) : HomeEvent()
    class ToggleHabit(val id: Int) : HomeEvent()
    class UpdateNote(val value: String) : HomeEvent()

    object AddTask : HomeEvent()
    object AddEvent : HomeEvent()
    object AddNote : HomeEvent()
    object AddExpense : HomeEvent()
}
