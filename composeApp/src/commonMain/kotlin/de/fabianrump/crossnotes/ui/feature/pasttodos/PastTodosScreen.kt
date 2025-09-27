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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import de.fabianrump.crossnotes.util.DatePickerDialog
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PastTodosScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val screenModel = koinViewModel<PastTodosScreenModel>()
    val state by screenModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

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
                },
                onEditTodo = { id, dueDate ->
                    screenModel.updateDatePickerVisibility(isVisible = true)
                    screenModel.updateSelectedTodoId(id = id)
                    screenModel.updateSelectedDueDate(date = dueDate)
                }
            )
        }
    )

    if (state.isDatePickerShown) {
        DatePickerDialog(
            show = state.isDatePickerShown,
            onDismissRequest = { screenModel.updateDatePickerVisibility(isVisible = false) },
            onDateSelected = { newDate ->
                screenModel.updateDatePickerVisibility(isVisible = false)
                screenModel.updateDueDate(date = newDate)
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Due date changed",
                        actionLabel = "Revert",
                        duration = SnackbarDuration.Long,
                        withDismissAction = true
                    )

                    if (result == SnackbarResult.ActionPerformed) screenModel.updateDueDate(date = state.selectedTodoDueDate)
                }
            },
        )
    }
}
