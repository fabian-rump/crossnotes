package de.fabianrump.crossnotes.ui.feature.settings

import de.fabianrump.crossnotes.ui.feature.settings.SettingsResult.ThemeToggled

internal object SettingsReducer {
    fun reduce(state: SettingsState, result: SettingsResult): SettingsState =
        when (result) {
            is ThemeToggled -> state.copy(themeMode = result.themeMode)
            is SettingsResult.Error -> state.copy(error = result.message)
            is SettingsResult.NotificationToggled -> state.copy(areNotificationsEnabled = result.isEnabled)
        }
}