package com.example.core_design.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Единый набор иконок.
 *
 * Используется в компонентах и на экранах приложения,
 * чтобы поддерживать единый визуальный стиль.
 */
object PlannerIcons {

    val Add: ImageVector = Icons.Default.Add
    val Edit: ImageVector = Icons.Default.Edit
    val Delete: ImageVector = Icons.Default.Delete
    val Check: ImageVector = Icons.Default.Check

    val Tasks: ImageVector = Icons.Default.Check
    val Calendar: ImageVector = Icons.Default.CalendarToday
    val Events: ImageVector = Icons.Default.Event
    val Habits: ImageVector = Icons.Default.Star
    val Goals: ImageVector = Icons.Default.Flag
    val Finance: ImageVector = Icons.Default.AccountBalanceWallet
    val Notes: ImageVector = Icons.Default.Notes
    val Schedule: ImageVector = Icons.Default.Schedule
}
