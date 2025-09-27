package de.fabianrump.crossnotes.ui.feature.pasttodos

import de.fabianrump.crossnotes.domain.models.Todo

internal data class PastTodosScreenState(
    val todos: List<Todo> = emptyList()
)