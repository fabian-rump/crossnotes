package de.fabianrump.crossnotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.fabianrump.crossnotes.ui.feature.addnote.AddNoteScreen
import de.fabianrump.crossnotes.ui.feature.history.HistoryScreen
import de.fabianrump.crossnotes.ui.feature.home.MVIHomeScreen
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosScreen
import de.fabianrump.crossnotes.ui.feature.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Composable
fun CrossNotesNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            MVIHomeScreen(
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
                }
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