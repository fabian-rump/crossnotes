package de.fabianrump.crossnotes.ui.feature.pasttodos

import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UpdateDueDateUseCase
import de.fabianrump.crossnotes.ui.extensions.isInPast
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosLabel.ShowErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosResult.Error
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosResult.Loading
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosResult.PastTodosLoaded
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

internal class PastTodosExecutor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val checkTodoUseCase: CheckTodoUseCase,
    private val uncheckTodoUseCase: UncheckTodoUseCase,
    private val updateDueDateUseCase: UpdateDueDateUseCase,
    private val scope: CoroutineScope,
    private val dispatch: (PastTodosResult) -> Unit,
    private val publish: (PastTodosLabel) -> Unit
) {
    fun execute(intent: PastTodosIntent) {
        when (intent) {
            PastTodosIntent.LoadPastTodos -> loadPastTodos()
            is PastTodosIntent.CheckTodo -> checkTodo(id = intent.id)
            is PastTodosIntent.UncheckTodo -> uncheckTodo(id = intent.id)
            is PastTodosIntent.UpdateDueDate -> updateDueDate(id = intent.id, date = intent.date)
            is PastTodosIntent.OpenDatePicker -> openDatePicker(id = intent.id, date = intent.date)
            PastTodosIntent.DismissDatePicker -> dismissDatePicker()
        }
    }

    private fun dismissDatePicker() {
        dispatch(PastTodosResult.DismissDatePicker)
    }

    private fun openDatePicker(id: Long, date: LocalDate) {
        dispatch(PastTodosResult.OpenDatePicker(id = id, date = date))
    }

    private fun updateDueDate(id: Long, date: LocalDate) {
        scope.launch {
            updateDueDateUseCase(id = id, dueDate = date)
        }
    }

    private fun uncheckTodo(id: Long) {
        scope.launch {
            uncheckTodoUseCase(id = id)
        }
    }

    private fun checkTodo(id: Long) {
        scope.launch {
            checkTodoUseCase(id = id)
        }
    }

    private fun loadPastTodos() {
        scope.launch {
            dispatch(Loading)
            try {
                getAllTodosUseCase().collect { todos ->
                    dispatch(PastTodosLoaded(todos = todos.filter { todo -> todo.isInPast() && todo.isCompleted.not() }))
                }
            } catch (e: Exception) {
                dispatch(Error(message = e.message ?: "Unknown error"))
                publish(ShowErrorSnackbar(message = "Failed to load notes"))
            }
        }
    }
}
