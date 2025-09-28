package de.fabianrump.crossnotes.ui.feature.pasttodos

internal sealed interface PastTodosLabel {
    data class ShowErrorSnackbar(val message: String) : PastTodosLabel
    data object NavigateToHistory : PastTodosLabel
}
