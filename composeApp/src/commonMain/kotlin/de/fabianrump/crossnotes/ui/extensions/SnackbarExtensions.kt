package de.fabianrump.crossnotes.ui.extensions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState

internal suspend fun SnackbarHostState.showErrorSnackbar(message: String) {
    showSnackbar(
        message = message,
        duration = SnackbarDuration.Short,
        withDismissAction = true
    )
}