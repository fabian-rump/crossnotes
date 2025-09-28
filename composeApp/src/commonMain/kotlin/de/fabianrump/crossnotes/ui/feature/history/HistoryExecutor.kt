package de.fabianrump.crossnotes.ui.feature.history

import de.fabianrump.crossnotes.domain.usecase.todo.GetHistoryTodosUseCase
import de.fabianrump.crossnotes.ui.feature.history.HistoryIntent.LoadHistory
import de.fabianrump.crossnotes.ui.feature.history.HistoryLabel.ShowErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.history.HistoryResult.Error
import de.fabianrump.crossnotes.ui.feature.history.HistoryResult.Loading
import de.fabianrump.crossnotes.ui.feature.history.HistoryResult.TodosLoaded
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class HistoryExecutor(
    private val getHistoryTodosUseCase: GetHistoryTodosUseCase,
    private val scope: CoroutineScope,
    private val dispatch: (HistoryResult) -> Unit,
    private val publish: (HistoryLabel) -> Unit
) {
    fun execute(intent: HistoryIntent) {
        when (intent) {
            LoadHistory -> loadHistory()
        }
    }

    private fun loadHistory() {
        scope.launch {
            dispatch(Loading)
            try {
                getHistoryTodosUseCase().collect { todos ->
                    dispatch(TodosLoaded(todos = todos))
                }
            } catch (e: Exception) {
                dispatch(Error(message = e.message ?: "Unknown error"))
                publish(ShowErrorSnackbar(message = "Failed to load notes"))
            }
        }
    }
}
