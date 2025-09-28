package de.fabianrump.crossnotes.ui.feature.home

import de.fabianrump.crossnotes.ui.feature.home.HomeResult.PastTodosExisting

internal object HomeReducer {
    fun reduce(state: HomeState, result: HomeResult): HomeState =
        when (result) {
            is HomeResult.Loading -> state.copy(isLoading = true, error = null)
            is HomeResult.TodosLoaded -> state.copy(isLoading = false, todos = result.todos)
            is HomeResult.Error -> state.copy(isLoading = false, error = result.message)
            is HomeResult.UsefulInfoLoaded -> state.copy(dailyUsefulInfo = result.usefulInfo)
            PastTodosExisting -> state.copy(pastTodosExists = true)
        }
}
