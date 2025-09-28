package de.fabianrump.crossnotes.ui.feature.home

import de.fabianrump.crossnotes.domain.models.Todo

internal data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val dailyUsefulInfo: String? = null,
    val pastTodosExists: Boolean = false,
    val todos: List<Todo> = emptyList()
)