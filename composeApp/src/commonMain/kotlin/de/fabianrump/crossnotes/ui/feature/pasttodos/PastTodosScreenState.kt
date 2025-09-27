package de.fabianrump.crossnotes.ui.feature.pasttodos

import de.fabianrump.crossnotes.domain.models.Todo
import de.fabianrump.crossnotes.ui.extensions.todayLocalDate
import kotlinx.datetime.LocalDate

internal data class PastTodosScreenState(
    val todos: List<Todo> = emptyList(),
    val isDatePickerShown: Boolean = false,
    val selectedTodoId: Long = 0L,
    val selectedTodoDueDate: LocalDate = todayLocalDate()
)