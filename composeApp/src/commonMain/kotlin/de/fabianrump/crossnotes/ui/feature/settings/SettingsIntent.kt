package de.fabianrump.crossnotes.ui.feature.settings

import de.fabianrump.crossnotes.data.model.ThemeMode

internal sealed interface SettingsIntent {
    data class ToggleTheme(val themeMode: ThemeMode) : SettingsIntent
    data class ToggleNotification(val isEnabled: Boolean) : SettingsIntent
    data object LoadTheme : SettingsIntent
    data object NavigateToHistory : SettingsIntent
    data object NavigateToAboutCrossNotes : SettingsIntent
    data object NavigateToPrivacySecurity : SettingsIntent
    data object NavigateToLanguageRegion : SettingsIntent
}