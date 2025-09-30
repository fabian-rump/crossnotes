package de.fabianrump.crossnotes.domain.usecase.theme

import de.fabianrump.crossnotes.data.model.ThemeMode
import de.fabianrump.crossnotes.domain.repositories.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow

internal class GetThemeUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<ThemeMode> = settingsRepository.getTheme()
}