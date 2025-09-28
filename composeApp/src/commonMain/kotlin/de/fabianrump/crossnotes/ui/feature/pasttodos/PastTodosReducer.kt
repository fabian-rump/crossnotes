package de.fabianrump.crossnotes.ui.feature.pasttodos

internal object PastTodosReducer {
    fun reduce(state: PastTodosState, result: PastTodosResult): PastTodosState =
        when (result) {
            is PastTodosResult.Error -> state.copy(isLoading = false, error = result.message)
            PastTodosResult.Loading -> state.copy(isLoading = true)
            is PastTodosResult.PastTodosLoaded -> state.copy(isLoading = false, todos = result.todos)
            is PastTodosResult.OpenDatePicker -> state.copy(isDatePickerShown = true, selectedTodoId = result.id, selectedTodoDueDate = result.date)
            PastTodosResult.DismissDatePicker -> state.copy(isDatePickerShown = false)
        }
}
