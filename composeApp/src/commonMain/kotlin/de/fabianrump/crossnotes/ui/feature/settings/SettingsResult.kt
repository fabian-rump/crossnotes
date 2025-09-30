package de.fabianrump.crossnotes.ui.feature.settings

import de.fabianrump.crossnotes.data.model.ThemeMode

internal sealed interface SettingsResult {
    data class ThemeToggled(val themeMode: ThemeMode) : SettingsResult
    data class NotificationToggled(val isEnabled: Boolean) : SettingsResult
    data class Error(val message: String) : SettingsResult
}