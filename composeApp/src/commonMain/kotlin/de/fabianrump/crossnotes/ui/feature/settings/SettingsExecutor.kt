package de.fabianrump.crossnotes.ui.feature.settings

import de.fabianrump.crossnotes.data.model.ThemeMode
import de.fabianrump.crossnotes.domain.usecase.theme.GetThemeUseCase
import de.fabianrump.crossnotes.domain.usecase.theme.ToggleThemeUseCase
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.LoadTheme
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.ToggleTheme
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToAboutCrossNotes
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToHistory
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToLanguageRegion
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToPrivacySecurity
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.ShowErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.settings.SettingsResult.Error
import de.fabianrump.crossnotes.ui.feature.settings.SettingsResult.ThemeToggled
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class SettingsExecutor(
    private val getThemeUseCase: GetThemeUseCase,
    private val toggleThemeUseCase: ToggleThemeUseCase,
    private val scope: CoroutineScope,
    private val dispatch: (SettingsResult) -> Unit,
    private val publish: (SettingsLabel) -> Unit
) {
    fun execute(intent: SettingsIntent) {
        when (intent) {
            is ToggleTheme -> toggleTheme(themeMode = intent.themeMode)
            LoadTheme -> loadTheme()
            SettingsIntent.NavigateToHistory -> navigateToHistory()
            is SettingsIntent.ToggleNotification -> toggleNotification(isEnabled = intent.isEnabled)
            SettingsIntent.NavigateToAboutCrossNotes -> navigateToAboutCrossNotes()
            SettingsIntent.NavigateToPrivacySecurity -> navigateToPrivacySecurity()
            SettingsIntent.NavigateToLanguageRegion -> navigateToLanguageRegion()
        }
    }

    private fun navigateToLanguageRegion() {
        publish(NavigateToLanguageRegion)
    }

    private fun navigateToPrivacySecurity() {
        publish(NavigateToPrivacySecurity)
    }

    private fun navigateToAboutCrossNotes() {
        publish(NavigateToAboutCrossNotes)
    }

    private fun navigateToHistory() {
        publish(NavigateToHistory)
    }

    private fun toggleNotification(isEnabled: Boolean) {
        Napier.d { "toggleNotification: $isEnabled" }
        dispatch(SettingsResult.NotificationToggled(isEnabled = isEnabled.not()))
    }

    private fun toggleTheme(themeMode: ThemeMode) {
        scope.launch {
            toggleThemeUseCase(themeMode = themeMode)
        }
    }

    private fun loadTheme() {
        scope.launch {
            try {
                getThemeUseCase().collect { theme ->
                    dispatch(ThemeToggled(themeMode = theme))
                }
            } catch (e: Exception) {
                dispatch(Error(message = e.message ?: "Unknown error"))
                publish(ShowErrorSnackbar(message = "Failed to load notes"))
            }
        }
    }
}
