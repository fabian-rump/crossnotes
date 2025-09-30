package de.fabianrump.crossnotes.ui.feature.settings

import de.fabianrump.crossnotes.data.model.ThemeMode

internal data class SettingsState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val error: String? = null,
    val areNotificationsEnabled: Boolean = false
)