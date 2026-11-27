package com.example.localization

import androidx.annotation.StringRes


object Strings {

    object Common {
        @StringRes val SAVE = R.string.action_save
        @StringRes val CANCEL = R.string.action_cancel
        @StringRes val DELETE = R.string.action_delete
        @StringRes val EDIT = R.string.action_edit
        @StringRes val ADD = R.string.action_add
        @StringRes val ADD_EVENT = R.string.action_add_event
    }

    object Home {
        @StringRes val GREETING = R.string.home_greeting
        @StringRes val SUBTITLE = R.string.home_subtitle
        @StringRes val PRIORITIES = R.string.home_priorities
        @StringRes val NO_TASKS = R.string.home_no_tasks
        @StringRes val MAIN_TODAY = R.string.home_main_today
        @StringRes val MAIN_TODAY_SUBTITLE = R.string.home_main_today_subtitle

        @StringRes val SCHEDULE = R.string.home_schedule
        @StringRes val SCHEDULE_SUBTITLE = R.string.home_schedule_subtitle
        @StringRes val NO_EVENTS = R.string.home_no_events

        @StringRes val HABITS = R.string.home_habits
        @StringRes val HABITS_SUBTITLE = R.string.home_habits_subtitle
        @StringRes val HABITS_PROGRESS = R.string.home_habits_progress
        @StringRes val NO_HABITS = R.string.home_no_habits

        @StringRes val GOALS = R.string.home_goals
        @StringRes val GOALS_SUBTITLE = R.string.home_goals_subtitle
        @StringRes val NO_ACTIVE_GOAL = R.string.home_no_active_goal
        @StringRes val GOAL_PROGRESS = R.string.home_goal_progress
        @StringRes val NEXT_STEP = R.string.home_next_step

        @StringRes val FINANCES = R.string.home_finances
        @StringRes val FINANCES_SUBTITLE = R.string.home_finances_subtitle
        @StringRes val BUDGET_BALANCE = R.string.home_budget_balance
        @StringRes val BUDGET_NOT_SET = R.string.home_budget_not_set
        @StringRes val BUDGET_AMOUNT = R.string.home_budget_amount
        @StringRes val CURRENCY_EURO = R.string.home_currency_euro
        @StringRes val DEMO_DATA = R.string.home_demo_data

        @StringRes val THOUGHT_OF_THE_DAY = R.string.home_thought_of_the_day
        @StringRes val THOUGHT_OF_THE_DAY_SUBTITLE = R.string.home_thought_of_the_day_subtitle
        @StringRes val NOTE_TITLE = R.string.home_note_title
        @StringRes val NOTE_PLACEHOLDER = R.string.home_note_placeholder

        @StringRes val QUICK_ADD = R.string.home_quick_add
        @StringRes val QUICK_ADD_SUBTITLE = R.string.home_quick_add_subtitle
        @StringRes val QUICK_ADD_TASK = R.string.home_quick_add_task
        @StringRes val QUICK_ADD_EVENT = R.string.home_quick_add_event
        @StringRes val QUICK_ADD_NOTE = R.string.home_quick_add_note
        @StringRes val QUICK_ADD_EXPENSE = R.string.home_quick_add_expense

        @StringRes val TASKS_PROGRESS = R.string.home_tasks_progress
        @StringRes val TASK_COMPLETED = R.string.home_task_completed
    }

    object Task {
        @StringRes val NEW = R.string.task_new
        @StringRes val EDIT = R.string.task_edit
        @StringRes val TITLE = R.string.task_title
        @StringRes val DATE = R.string.task_date
        @StringRes val DESCRIPTION = R.string.task_description
        @StringRes val DELETE_TITLE = R.string.task_delete_title
        @StringRes val DELETE_MESSAGE = R.string.task_delete_message
        @StringRes val DELETE_CANCEL = R.string.task_delete_cancel
    }
}

