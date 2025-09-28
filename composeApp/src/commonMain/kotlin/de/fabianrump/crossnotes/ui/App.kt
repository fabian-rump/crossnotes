package de.fabianrump.crossnotes.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import de.fabianrump.crossnotes.navigation.CrossNotesNavigation
import de.fabianrump.crossnotes.ui.theme.AppDimensions
import de.fabianrump.crossnotes.ui.theme.LocalAppDimensions
import de.fabianrump.crossnotes.ui.theme.crossNotesTypography
import de.fabianrump.crossnotes.ui.theme.darkScheme
import de.fabianrump.crossnotes.ui.theme.lightScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
internal fun App() {
    CompositionLocalProvider(LocalAppDimensions provides AppDimensions()) {
        MaterialTheme(
            colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme,
            typography = crossNotesTypography(),
            content = {
                CrossNotesNavigation()
            }
        )
    }
}