package de.fabianrump.crossnotes.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import de.fabianrump.crossnotes.data.model.ThemeMode
import de.fabianrump.crossnotes.domain.usecase.theme.GetThemeUseCase
import de.fabianrump.crossnotes.navigation.CrossNotesNavigation
import de.fabianrump.crossnotes.ui.theme.AppDimensions
import de.fabianrump.crossnotes.ui.theme.LocalAppDimensions
import de.fabianrump.crossnotes.ui.theme.crossNotesTypography
import de.fabianrump.crossnotes.ui.theme.darkScheme
import de.fabianrump.crossnotes.ui.theme.highContrastDarkColorScheme
import de.fabianrump.crossnotes.ui.theme.highContrastLightColorScheme
import de.fabianrump.crossnotes.ui.theme.lightScheme
import de.fabianrump.crossnotes.ui.theme.mediumContrastDarkColorScheme
import de.fabianrump.crossnotes.ui.theme.mediumContrastLightColorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
internal fun App() {
    val getThemeUseCase: GetThemeUseCase = koinInject()
    val currentTheme by getThemeUseCase().collectAsState(initial = ThemeMode.SYSTEM)
    val theme = when (currentTheme) {
        ThemeMode.LIGHT -> lightScheme
        ThemeMode.LIGHT_MEDIUM -> mediumContrastLightColorScheme
        ThemeMode.LIGHT_HIGH -> highContrastLightColorScheme
        ThemeMode.DARK -> darkScheme
        ThemeMode.DARK_MEDIUM -> mediumContrastDarkColorScheme
        ThemeMode.DARK_HIGH -> highContrastDarkColorScheme
        ThemeMode.SYSTEM -> if (isSystemInDarkTheme()) darkScheme else lightScheme
    }

    CompositionLocalProvider(LocalAppDimensions provides AppDimensions()) {
        MaterialTheme(
            colorScheme = theme,
            typography = crossNotesTypography(),
            content = {
                CrossNotesNavigation()
            }
        )
    }
}