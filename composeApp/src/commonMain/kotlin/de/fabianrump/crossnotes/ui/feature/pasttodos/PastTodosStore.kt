package de.fabianrump.crossnotes.ui.feature.pasttodos

import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UpdateDueDateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class PastTodosStore(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val checkTodoUseCase: CheckTodoUseCase,
    private val uncheckTodoUseCase: UncheckTodoUseCase,
    private val updateDueDateUseCase: UpdateDueDateUseCase,
    scope: CoroutineScope
) {
    private val _state = MutableStateFlow(PastTodosState())
    val state: StateFlow<PastTodosState> = _state.asStateFlow()

    private val _labels = MutableSharedFlow<PastTodosLabel>()
    val labels: SharedFlow<PastTodosLabel> = _labels.asSharedFlow()

    private val executor = PastTodosExecutor(
        getAllTodosUseCase = getAllTodosUseCase,
        checkTodoUseCase = checkTodoUseCase,
        uncheckTodoUseCase = uncheckTodoUseCase,
        updateDueDateUseCase = updateDueDateUseCase,
        scope = scope,
        dispatch = { result ->
            _state.value = PastTodosReducer.reduce(_state.value, result)
        },
        publish = { label ->
            scope.launch { _labels.emit(label) }
        }
    )

    fun onIntent(intent: PastTodosIntent) {
        executor.execute(intent)
    }
}
