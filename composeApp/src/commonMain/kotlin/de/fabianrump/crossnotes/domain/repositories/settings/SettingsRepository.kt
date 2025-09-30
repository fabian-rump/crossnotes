package de.fabianrump.crossnotes.domain.repositories.settings

import de.fabianrump.crossnotes.data.model.ThemeMode
import kotlinx.coroutines.flow.Flow

internal interface SettingsRepository {

    suspend fun toggleTheme(themeMode: ThemeMode)

    fun getTheme(): Flow<ThemeMode>
}