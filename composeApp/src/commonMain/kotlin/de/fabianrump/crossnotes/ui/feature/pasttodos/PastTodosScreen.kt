package de.fabianrump.crossnotes.ui.feature.pasttodos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import de.fabianrump.crossnotes.ui.extensions.showErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosIntent.DismissDatePicker
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosIntent.LoadPastTodos
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosIntent.UpdateDueDate
import de.fabianrump.crossnotes.util.DatePickerDialog
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PastTodosScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val store: PastTodosStore = koinInject { parametersOf(scope) }
    val state by store.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        store.onIntent(intent = LoadPastTodos)
    }

    LaunchedEffect(key1 = store) {
        store.labels.collect { label ->
            when (label) {
                is PastTodosLabel.ShowErrorSnackbar -> scope.launch {
                    snackbarHostState.showErrorSnackbar(message = label.message)
                }

                PastTodosLabel.NavigateToHistory -> onNavigateToHistory()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Past Todos") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onNavigateToHistory,
                        content = {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "History"
                            )
                        }
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { paddingValues ->
            PastTodosScreenContent(
                paddingValues = paddingValues,
                state = state,
                onIntent = store::onIntent,
            )
        }
    )

    if (state.isDatePickerShown) {
        DatePickerDialog(
            show = state.isDatePickerShown,
            onDismissRequest = { store.onIntent(intent = DismissDatePicker) },
            onDateSelected = { newDate ->
                store.onIntent(intent = DismissDatePicker)
                store.onIntent(intent = UpdateDueDate(id = state.selectedTodoId, date = newDate))
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Due date changed",
                        actionLabel = "Revert",
                        duration = SnackbarDuration.Long,
                        withDismissAction = true
                    )

                    if (result == SnackbarResult.ActionPerformed) store.onIntent(
                        intent = UpdateDueDate(
                            id = state.selectedTodoId,
                            date = state.selectedTodoDueDate
                        )
                    )
                }
            },
        )
    }
}
