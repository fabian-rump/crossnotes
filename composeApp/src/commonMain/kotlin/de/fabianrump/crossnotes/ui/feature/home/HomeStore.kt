package de.fabianrump.crossnotes.ui.feature.home

import de.fabianrump.crossnotes.domain.usecase.info.UsefulInfoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class HomeStore(
    private val usefulInfoUseCase: UsefulInfoUseCase,
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val checkTodoUseCase: CheckTodoUseCase,
    private val uncheckTodoUseCase: UncheckTodoUseCase,
    scope: CoroutineScope
) {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _labels = MutableSharedFlow<HomeLabel>()
    val labels: SharedFlow<HomeLabel> = _labels.asSharedFlow()

    private val executor = HomeExecutor(
        usefulInfoUseCase = usefulInfoUseCase,
        getAllTodosUseCase = getAllTodosUseCase,
        checkTodoUseCase = checkTodoUseCase,
        uncheckTodoUseCase = uncheckTodoUseCase,
        scope = scope,
        dispatch = { result ->
            _state.value = HomeReducer.reduce(_state.value, result)
        },
        publish = { label ->
            scope.launch { _labels.emit(label) }
        }
    )

    fun onIntent(intent: HomeIntent) {
        executor.execute(intent)
    }
}
