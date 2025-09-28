package de.fabianrump.crossnotes.ui.feature.history

import de.fabianrump.crossnotes.domain.usecase.todo.GetHistoryTodosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class HistoryStore(
    private val getHistoryTodosUseCase: GetHistoryTodosUseCase,
    scope: CoroutineScope
) {
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    private val _labels = MutableSharedFlow<HistoryLabel>()
    val labels: SharedFlow<HistoryLabel> = _labels.asSharedFlow()

    private val executor = HistoryExecutor(
        getHistoryTodosUseCase = getHistoryTodosUseCase,
        scope = scope,
        dispatch = { result ->
            _state.value = HistoryReducer.reduce(_state.value, result)
        },
        publish = { label ->
            scope.launch { _labels.emit(label) }
        }
    )

    fun onIntent(intent: HistoryIntent) {
        executor.execute(intent)
    }
}
