package de.fabianrump.crossnotes.ui.feature.home.mvvm

import de.fabianrump.crossnotes.domain.models.Todo

internal data class HomeScreenState(
    val dailyUsefulInfo: String? = null,
    val pastTodosExists: Boolean = false,
    val todos: List<Todo> = emptyList()
)
