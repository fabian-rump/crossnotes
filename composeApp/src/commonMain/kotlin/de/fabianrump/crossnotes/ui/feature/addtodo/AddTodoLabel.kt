package de.fabianrump.crossnotes.ui.feature.addtodo

internal sealed interface AddTodoLabel {
    data class ShowErrorSnackbar(val message: String) : AddTodoLabel
    data object NavigateBack : AddTodoLabel
}
