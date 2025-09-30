package de.fabianrump.crossnotes.ui.feature.settings

internal sealed interface SettingsLabel {
    data class ShowErrorSnackbar(val message: String) : SettingsLabel
    data object NavigateToHistory : SettingsLabel
    data object NavigateToAboutCrossNotes : SettingsLabel
    data object NavigateToPrivacySecurity : SettingsLabel
    data object NavigateToLanguageRegion : SettingsLabel
}
