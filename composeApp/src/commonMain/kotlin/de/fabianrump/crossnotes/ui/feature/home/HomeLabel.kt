package de.fabianrump.crossnotes.ui.feature.home

internal sealed interface HomeLabel {
    data class ShowErrorSnackbar(val message: String) : HomeLabel
    data class ShowUncheckTodoSnackbar(val id: Long) : HomeLabel
    data object NavigateToAddTodo : HomeLabel
    data object NavigateToSettings : HomeLabel
    data object NavigateToPastTodos : HomeLabel
}
