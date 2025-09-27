package de.fabianrump.crossnotes.ui.feature.history

import de.fabianrump.crossnotes.domain.models.Todo

internal data class HistoryScreenState(
    val todos: List<Todo> = emptyList()
)