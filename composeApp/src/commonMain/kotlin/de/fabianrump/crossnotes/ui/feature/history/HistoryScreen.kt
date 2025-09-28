package de.fabianrump.crossnotes.ui.feature.history

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
import de.fabianrump.crossnotes.ui.feature.history.HistoryIntent.LoadHistory
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HistoryScreen(
    onNavigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val store: HistoryStore = koinInject { parametersOf(scope) }
    val state by store.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        store.onIntent(intent = LoadHistory)
    }

    LaunchedEffect(key1 = store) {
        store.labels.collect { label ->
            when (label) {
                is HistoryLabel.ShowErrorSnackbar -> scope.launch {
                    snackbarHostState.showErrorSnackbar(message = label.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "History") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        content = { paddingValues ->
            HistoryScreenContent(
                paddingValues = paddingValues,
                state = state,
            )
        }
    )
}