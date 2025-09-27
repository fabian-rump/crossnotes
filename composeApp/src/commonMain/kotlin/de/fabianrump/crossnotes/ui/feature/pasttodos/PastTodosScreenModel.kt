package de.fabianrump.crossnotes.ui.feature.pasttodos

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.ui.extensions.isInPast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PastTodosScreenModel(
    private val getAllTodosUseCase: GetAllTodosUseCase
) : ScreenModel {

    private val _uiState = MutableStateFlow(PastTodosScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPastTodos()
    }

    private fun loadPastTodos() {
        screenModelScope.launch {
            getAllTodosUseCase().collect { todos ->
                _uiState.update {
                    it.copy(
                        todos = todos.filter { todo -> todo.isInPast() }
                    )
                }
            }
        }
    }
}