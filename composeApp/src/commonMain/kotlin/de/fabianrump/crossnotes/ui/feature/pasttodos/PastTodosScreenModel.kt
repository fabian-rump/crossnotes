package de.fabianrump.crossnotes.ui.feature.pasttodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UpdateDueDateUseCase
import de.fabianrump.crossnotes.ui.extensions.isInPast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

internal class PastTodosScreenModel(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val checkTodoUseCase: CheckTodoUseCase,
    private val uncheckTodoUseCase: UncheckTodoUseCase,
    private val updateDueDateUseCase: UpdateDueDateUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PastTodosScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPastTodos()
    }

    fun checkTodo(id: Long) {
        viewModelScope.launch {
            checkTodoUseCase(id = id)
        }
    }

    fun uncheckTodo(id: Long) {
        viewModelScope.launch {
            uncheckTodoUseCase(id = id)
        }
    }

    fun updateDatePickerVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(isDatePickerShown = isVisible) }
    }

    fun updateSelectedTodoId(id: Long) {
        _uiState.update { it.copy(selectedTodoId = id) }
    }

    fun updateSelectedDueDate(date: LocalDate) {
        _uiState.update { it.copy(selectedTodoDueDate = date) }
    }

    fun updateDueDate(date: LocalDate) {
        viewModelScope.launch {
            updateDueDateUseCase(id = uiState.value.selectedTodoId, dueDate = date)
        }
    }

    private fun loadPastTodos() {
        viewModelScope.launch {
            getAllTodosUseCase().collect { todos ->
                _uiState.update {
                    it.copy(
                        todos = todos.filter { todo -> todo.isInPast() && todo.isCompleted.not() }
                    )
                }
            }
        }
    }
}