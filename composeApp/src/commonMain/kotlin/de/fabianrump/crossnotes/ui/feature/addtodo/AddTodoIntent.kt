package de.fabianrump.crossnotes.ui.feature.addtodo

import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate

internal sealed interface AddTodoIntent {
    data class SaveTodo(val text: String, val priority: Priority, val dueDate: LocalDate) : AddTodoIntent
    data object OpenDatePicker : AddTodoIntent
    data object DismissDatePicker : AddTodoIntent
    data class ChangePriority(val priority: Priority) : AddTodoIntent
    data class ChangeText(val text: String) : AddTodoIntent
    data class ChangeDueDate(val date: LocalDate) : AddTodoIntent
}