package de.fabianrump.crossnotes.ui.feature.addtodo

import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class AddTodoStore(
    private val addTodoUseCase: AddTodoUseCase,
    scope: CoroutineScope
) {
    private val _state = MutableStateFlow(AddTodoState())
    val state: StateFlow<AddTodoState> = _state.asStateFlow()

    private val _labels = MutableSharedFlow<AddTodoLabel>()
    val labels: SharedFlow<AddTodoLabel> = _labels.asSharedFlow()

    private val executor = AddTodoExecutor(
        addTodoUseCase = addTodoUseCase,
        scope = scope,
        dispatch = { result ->
            _state.value = AddTotoReducer.reduce(_state.value, result)
        },
        publish = { label ->
            scope.launch { _labels.emit(label) }
        }
    )

    fun onIntent(intent: AddTodoIntent) {
        executor.execute(intent)
    }
}
