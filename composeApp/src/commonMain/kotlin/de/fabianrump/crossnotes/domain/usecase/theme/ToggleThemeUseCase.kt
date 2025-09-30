package de.fabianrump.crossnotes.domain.usecase.theme

import de.fabianrump.crossnotes.data.model.ThemeMode
import de.fabianrump.crossnotes.domain.repositories.settings.SettingsRepository

internal class ToggleThemeUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        settingsRepository.toggleTheme(themeMode = themeMode)
    }
}