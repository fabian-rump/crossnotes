package de.fabianrump.crossnotes.ui.feature.addnote

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import de.fabianrump.crossnotes.util.DatePickerDialog

internal class AddNoteScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: AddNoteScreenModel = koinScreenModel()
        val state by screenModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Add Note") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
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
                onTextChange = screenModel::onTextChange,
                onDueDateClick = {
                    screenModel.updateDatePickerVisibility(isVisible = true)
                },
                onCreateTodoClick = screenModel::saveTodo,
                onPriorityChange = screenModel::updatePriority,
            )
        }

        if (state.isDatePickerShown) {
            DatePickerDialog(
                show = state.isDatePickerShown,
                onDismissRequest = { screenModel.updateDatePickerVisibility(isVisible = false) },
                onDateSelected = { newDate ->
                    screenModel.updateDueDate(date = newDate)
                    screenModel.updateDatePickerVisibility(isVisible = false)
                },
                initialDate = state.dueDate
            )
        }
    }
}