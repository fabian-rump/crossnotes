package de.fabianrump.crossnotes.ui.feature.home

internal sealed interface HomeIntent {
    data object LoadInitialData : HomeIntent
    data class UncheckTodo(val id: Long) : HomeIntent
    data class CheckTodo(val id: Long) : HomeIntent
    data object NavigateToSettings: HomeIntent
    data object NavigateToPastTodos: HomeIntent
}