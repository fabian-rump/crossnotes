package de.fabianrump.crossnotes.ui.feature.pasttodos

import de.fabianrump.crossnotes.domain.models.Todo
import kotlinx.datetime.LocalDate

internal sealed interface PastTodosResult {
    data object Loading : PastTodosResult
    data class PastTodosLoaded(val todos: List<Todo>) : PastTodosResult
    data class Error(val message: String) : PastTodosResult
    data class OpenDatePicker(val id: Long, val date: LocalDate) : PastTodosResult
    data object DismissDatePicker : PastTodosResult
}