package de.fabianrump.crossnotes.ui.feature.history

internal sealed interface HistoryLabel {
    data class ShowErrorSnackbar(val message: String) : HistoryLabel
}
