package de.fabianrump.crossnotes.ui.feature.addtodo

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
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.ChangeDueDate
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.DismissDatePicker
import de.fabianrump.crossnotes.util.DatePickerDialog
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddNoteScreen(
    onNavigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val store: AddTodoStore = koinInject { parametersOf(scope) }
    val state by store.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = store) {
        store.labels.collect { label ->
            when (label) {
                is AddTodoLabel.ShowErrorSnackbar -> scope.launch {
                    snackbarHostState.showErrorSnackbar(message = label.message)
                }

                AddTodoLabel.NavigateBack -> onNavigateBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Note") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        AddNoteScreenContent(
            paddingValues = innerPadding,
            state = state,
            onIntent = store::onIntent,
        )
    }

    if (state.isDatePickerShown) {
        DatePickerDialog(
            show = state.isDatePickerShown,
            onDismissRequest = { store.onIntent(intent = DismissDatePicker) },
            onDateSelected = { newDate ->
                store.onIntent(intent = ChangeDueDate(date = newDate))
            },
            initialDate = state.dueDate
        )
    }
}