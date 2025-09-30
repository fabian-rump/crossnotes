package de.fabianrump.crossnotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.fabianrump.crossnotes.ui.feature.addtodo.AddNoteScreen
import de.fabianrump.crossnotes.ui.feature.history.HistoryScreen
import de.fabianrump.crossnotes.ui.feature.home.HomeScreen
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosScreen
import de.fabianrump.crossnotes.ui.feature.settings.SettingsScreen
import de.fabianrump.crossnotes.ui.feature.settings.subscreens.AboutCrossNotesScreen
import de.fabianrump.crossnotes.ui.feature.settings.subscreens.LanguageRegionScreen
import de.fabianrump.crossnotes.ui.feature.settings.subscreens.PrivacySecurityScreen
import kotlinx.serialization.Serializable

@Composable
fun CrossNotesNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onSettingsClick = {
                    navController.navigate(route = Settings)
                },
                onFabClick = {
                    navController.navigate(route = AddNote)
                },
                onPastTodoInfoCardClick = {
                    navController.navigate(route = PastTodos)
                }
            )
        }
        composable<AddNote> {
            AddNoteScreen(
                onNavigateBack = navController::popBackStack
            )
        }
        composable<History> {
            HistoryScreen(
                onNavigateBack = navController::popBackStack
            )
        }
        composable<PastTodos> {
            PastTodosScreen(
                onNavigateBack = navController::popBackStack,
                onNavigateToHistory = {
                    navController.navigate(route = History)
                }
            )
        }
        composable<Settings> {
            SettingsScreen(
                onNavigateBack = navController::popBackStack,
                onNavigateToHistory = {
                    navController.navigate(route = History)
                },
                onNavigateToAboutCrossNotes = {
                    navController.navigate(route = AboutCrossNotes)
                },
                onNavigateToPrivacySecurity = {
                    navController.navigate(route = PrivacySecurity)
                },
                onNavigateToLanguageRegion = {
                    navController.navigate(route = LanguageRegion)
                }
            )
        }
        composable<AboutCrossNotes> {
            AboutCrossNotesScreen(
                onNavigateBack = navController::popBackStack,
            )
        }
        composable<PrivacySecurity> {
            PrivacySecurityScreen(
                onNavigateBack = navController::popBackStack,
            )
        }
        composable<LanguageRegion> {
            LanguageRegionScreen(
                onNavigateBack = navController::popBackStack,
            )
        }
    }
}

@Serializable
private data object Home

@Serializable
private data object AddNote

@Serializable
private data object History

@Serializable
private data object PastTodos

@Serializable
private data object Settings

@Serializable
private data object AboutCrossNotes

@Serializable
private data object PrivacySecurity

@Serializable
private data object LanguageRegion