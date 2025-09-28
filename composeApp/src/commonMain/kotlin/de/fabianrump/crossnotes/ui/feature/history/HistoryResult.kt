package de.fabianrump.crossnotes.ui.feature.history

import de.fabianrump.crossnotes.domain.models.Todo

internal sealed interface HistoryResult {
    data object Loading : HistoryResult
    data class TodosLoaded(val todos: List<Todo>) : HistoryResult
    data class Error(val message: String) : HistoryResult
}