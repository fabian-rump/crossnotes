package de.fabianrump.crossnotes.ui.feature.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import de.fabianrump.crossnotes.ui.extensions.showErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.LoadTheme
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToAboutCrossNotes
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToHistory
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.NavigateToPrivacySecurity
import de.fabianrump.crossnotes.ui.feature.settings.SettingsLabel.ShowErrorSnackbar
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToAboutCrossNotes: () -> Unit,
    onNavigateToPrivacySecurity: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val store: SettingsStore = koinInject { parametersOf(scope) }
    val state by store.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        store.onIntent(intent = LoadTheme)
    }

    LaunchedEffect(key1 = store) {
        store.labels.collect { label ->
            when (label) {
                is ShowErrorSnackbar -> scope.launch {
                    snackbarHostState.showErrorSnackbar(message = label.message)
                }

                NavigateToHistory -> onNavigateToHistory()
                NavigateToAboutCrossNotes -> onNavigateToAboutCrossNotes()
                NavigateToPrivacySecurity -> onNavigateToPrivacySecurity()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            SettingsScreenContent(
                paddingValues = paddingValues,
                state = state,
                onIntent = store::onIntent
            )
        }
    )
}