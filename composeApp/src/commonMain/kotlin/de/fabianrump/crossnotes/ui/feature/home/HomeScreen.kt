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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    onSettingsClick: () -> Unit,
    onFabClick: () -> Unit,
    onPastTodoInfoCardClick: () -> Unit
) {
    val screenModel = koinViewModel<HomeScreenModel>()
    val uiState by screenModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Neue Notiz erstellen"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerPadding ->
            HomeScreenContent(
                paddingValues = innerPadding,
                uiState = uiState,
                onSettingsClick = onSettingsClick,
                onPastTodoInfoCardClick = onPastTodoInfoCardClick,
                onCheckedTodo = {
                    screenModel.checkTodo(id = it)
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Todo completed",
                            actionLabel = "Revert",
                            duration = SnackbarDuration.Long,
                            withDismissAction = true
                        )

                        if (result == SnackbarResult.ActionPerformed) screenModel.uncheckTodo(id = it)
                    }
                }
            )
        }
    )
}
