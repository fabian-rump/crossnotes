package de.fabianrump.crossnotes.ui.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import crossnotes.composeapp.generated.resources.Res
import crossnotes.composeapp.generated.resources.home_todo_completed_snackbar_message
import de.fabianrump.crossnotes.ui.extensions.showErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.LoadInitialData
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.UncheckTodo
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
internal fun HomeScreen(
    onSettingsClick: () -> Unit,
    onFabClick: () -> Unit,
    onPastTodoInfoCardClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val store: HomeStore = koinInject { parametersOf(scope) }
    val state by store.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        store.onIntent(intent = LoadInitialData)
    }

    LaunchedEffect(key1 = store) {
        store.labels.collect { label ->
            when (label) {
                HomeLabel.NavigateToAddTodo -> onFabClick()
                HomeLabel.NavigateToPastTodos -> onPastTodoInfoCardClick()
                HomeLabel.NavigateToSettings -> onSettingsClick()
                is HomeLabel.ShowErrorSnackbar -> scope.launch {
                    snackbarHostState.showErrorSnackbar(message = label.message)
                }

                is HomeLabel.ShowUncheckTodoSnackbar -> scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = getString(resource = Res.string.home_todo_completed_snackbar_message),
                        actionLabel = "Revert",
                        duration = SnackbarDuration.Long,
                        withDismissAction = true
                    )

                    if (result == SnackbarResult.ActionPerformed) store.onIntent(intent = UncheckTodo(id = label.id))
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Note"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerPadding ->
            HomeScreenContent(
                paddingValues = innerPadding,
                state = state,
                onIntent = store::onIntent,
            )
        }
    )
}
