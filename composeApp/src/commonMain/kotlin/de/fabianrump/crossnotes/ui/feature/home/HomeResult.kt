package de.fabianrump.crossnotes.ui.feature.home

import de.fabianrump.crossnotes.domain.models.Todo

internal sealed interface HomeResult {
    data object Loading : HomeResult
    data class TodosLoaded(val todos: List<Todo>) : HomeResult
    data class UsefulInfoLoaded(val usefulInfo: String) : HomeResult
    data object PastTodosExisting : HomeResult
    data class Error(val message: String) : HomeResult
}