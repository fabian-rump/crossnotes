package de.fabianrump.crossnotes.ui.feature.home.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.crossnotes.domain.usecase.info.UsefulInfoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import de.fabianrump.crossnotes.ui.extensions.isInPast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeScreenModel(
    private val usefulInfoUseCase: UsefulInfoUseCase,
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val checkTodoUseCase: CheckTodoUseCase,
    private val uncheckTodoUseCase: UncheckTodoUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(dailyUsefulInfo = usefulInfoUseCase()) }
        loadUnfinishedRecentsTodos()
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

    private fun loadUnfinishedRecentsTodos() {
        viewModelScope.launch {
            getAllTodosUseCase().collect { todos ->
                _uiState.update {
                    it.copy(
                        pastTodosExists = todos.any { todo -> todo.isInPast() },
                        todos = todos.filter { todo -> todo.isInPast().not() and todo.isCompleted.not() }
                    )
                }
            }
        }
    }
}
