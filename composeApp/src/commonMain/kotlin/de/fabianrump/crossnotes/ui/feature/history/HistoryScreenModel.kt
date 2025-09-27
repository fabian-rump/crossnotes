package de.fabianrump.crossnotes.ui.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.crossnotes.domain.usecase.todo.GetHistoryTodosUseCase
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HistoryScreenModel(
    private val getHistoryTodosUseCase: GetHistoryTodosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            getHistoryTodosUseCase().collect { todos ->
                _uiState.update {
                    it.copy(
                        todos = todos
                    )
                }
            }
        }
    }
}