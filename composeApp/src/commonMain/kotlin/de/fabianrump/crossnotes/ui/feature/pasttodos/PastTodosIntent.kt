package de.fabianrump.crossnotes.ui.feature.pasttodos

import kotlinx.datetime.LocalDate

internal sealed interface PastTodosIntent {
    data object LoadPastTodos : PastTodosIntent
    data class CheckTodo(val id: Long) : PastTodosIntent
    data class UncheckTodo(val id: Long) : PastTodosIntent
    data class OpenDatePicker(val id: Long, val date: LocalDate) : PastTodosIntent
    data class UpdateDueDate(val id: Long, val date: LocalDate) : PastTodosIntent
    data object DismissDatePicker : PastTodosIntent
}