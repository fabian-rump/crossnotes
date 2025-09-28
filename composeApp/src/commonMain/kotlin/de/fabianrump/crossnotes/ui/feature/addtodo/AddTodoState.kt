package de.fabianrump.crossnotes.ui.feature.addtodo

import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate

internal data class AddTodoState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val text: String = "",
    val dueDate: LocalDate? = null,
    val isDatePickerShown: Boolean = false,
    val priority: Priority = Priority.LOW
)
