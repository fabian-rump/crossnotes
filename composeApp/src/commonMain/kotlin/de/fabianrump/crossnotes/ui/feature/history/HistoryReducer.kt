package de.fabianrump.crossnotes.ui.feature.history

internal object HistoryReducer {
    fun reduce(state: HistoryState, result: HistoryResult): HistoryState =
        when (result) {
            is HistoryResult.Error -> state.copy(isLoading = false, error = result.message)
            HistoryResult.Loading -> state.copy(isLoading = true)
            is HistoryResult.TodosLoaded -> state.copy(isLoading = false, todos = result.todos)
        }
}
