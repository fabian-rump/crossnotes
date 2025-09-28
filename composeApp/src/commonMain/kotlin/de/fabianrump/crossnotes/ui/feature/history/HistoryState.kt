package de.fabianrump.crossnotes.ui.feature.history

import de.fabianrump.crossnotes.domain.models.Todo

internal data class HistoryState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val todos: List<Todo> = emptyList()
)