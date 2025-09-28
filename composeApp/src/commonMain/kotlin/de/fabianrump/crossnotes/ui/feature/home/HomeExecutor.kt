package de.fabianrump.crossnotes.ui.feature.home

import de.fabianrump.crossnotes.domain.usecase.info.UsefulInfoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import de.fabianrump.crossnotes.ui.extensions.isInPast
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.CheckTodo
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.LoadInitialData
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.NavigateToPastTodos
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.NavigateToSettings
import de.fabianrump.crossnotes.ui.feature.home.HomeIntent.UncheckTodo
import de.fabianrump.crossnotes.ui.feature.home.HomeLabel.ShowUncheckTodoSnackbar
import de.fabianrump.crossnotes.ui.feature.home.HomeResult.PastTodosExisting
import de.fabianrump.crossnotes.ui.feature.home.HomeResult.UsefulInfoLoaded
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class HomeExecutor(
    private val usefulInfoUseCase: UsefulInfoUseCase,
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val checkTodoUseCase: CheckTodoUseCase,
    private val uncheckTodoUseCase: UncheckTodoUseCase,
    private val scope: CoroutineScope,
    private val dispatch: (HomeResult) -> Unit,
    private val publish: (HomeLabel) -> Unit
) {
    fun execute(intent: HomeIntent) {
        when (intent) {
            is CheckTodo -> {
                checkTodo(id = intent.id)
                publish(ShowUncheckTodoSnackbar(id = intent.id))
            }

            is UncheckTodo -> uncheckTodo(id = intent.id)
            LoadInitialData -> loadInitialData()
            NavigateToPastTodos -> navigateToPastTodos()
            NavigateToSettings -> navigateToSettings()
        }
    }

    private fun navigateToSettings() {
        publish(HomeLabel.NavigateToSettings)
    }

    private fun navigateToPastTodos() {
        publish(HomeLabel.NavigateToPastTodos)
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

    private fun loadInitialData() {
        scope.launch {
            dispatch(HomeResult.Loading)
            try {
                dispatch(UsefulInfoLoaded(usefulInfo = usefulInfoUseCase()))
                getAllTodosUseCase().collect { todos ->
                    if (todos.any { todo -> todo.isInPast() and todo.isCompleted.not() }) dispatch(PastTodosExisting)
                    dispatch(HomeResult.TodosLoaded(todos = todos.filter { todo -> todo.isInPast().not() and todo.isCompleted.not() }))
                }
            } catch (e: Exception) {
                dispatch(HomeResult.Error(e.message ?: "Unknown error"))
                publish(HomeLabel.ShowErrorSnackbar("Failed to load notes"))
            }
        }
    }
}
