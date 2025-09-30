package de.fabianrump.crossnotes.ui.feature.addtodo

import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate

internal sealed interface AddTodoResult {
    data object Loading : AddTodoResult
    data class Error(val message: String) : AddTodoResult
    data class ChangeDueDate(val date: LocalDate) : AddTodoResult
    data class ChangePriority(val priority: Priority) : AddTodoResult
    data class ChangeText(val text: String) : AddTodoResult
    data object OpenDatePicker : AddTodoResult
    data object DismissDatePicker : AddTodoResult
    data class HolidaysFetched(val holidays: List<String>) : AddTodoResult
}