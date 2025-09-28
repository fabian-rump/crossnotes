package de.fabianrump.crossnotes.ui.feature.history

internal sealed interface HistoryIntent {
    data object LoadHistory : HistoryIntent
}