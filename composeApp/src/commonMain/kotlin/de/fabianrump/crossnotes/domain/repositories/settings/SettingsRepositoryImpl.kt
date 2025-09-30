package de.fabianrump.crossnotes.domain.repositories.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import de.fabianrump.crossnotes.data.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.cancellation.CancellationException

internal class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    override suspend fun toggleTheme(themeMode: ThemeMode) {
        try {
            dataStore.edit {
                it[stringPreferencesKey(name = "THEME_KEY")] = themeMode.name
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
        }
    }

    override fun getTheme(): Flow<ThemeMode> {
        return dataStore.data.map {
            val themeMode = it[stringPreferencesKey(name = "THEME_KEY")] ?: ThemeMode.SYSTEM.name
            ThemeMode.valueOf(value = themeMode)
        }
    }
}