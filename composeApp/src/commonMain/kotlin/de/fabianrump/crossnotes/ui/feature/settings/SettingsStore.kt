package de.fabianrump.crossnotes.ui.feature.settings

import de.fabianrump.crossnotes.domain.usecase.theme.GetThemeUseCase
import de.fabianrump.crossnotes.domain.usecase.theme.ToggleThemeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SettingsStore(
    private val getThemeUseCase: GetThemeUseCase,
    private val toggleThemeUseCase: ToggleThemeUseCase,
    scope: CoroutineScope
) {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    private val _labels = MutableSharedFlow<SettingsLabel>()
    val labels: SharedFlow<SettingsLabel> = _labels.asSharedFlow()

    private val executor = SettingsExecutor(
        getThemeUseCase = getThemeUseCase,
        toggleThemeUseCase = toggleThemeUseCase,
        scope = scope,
        dispatch = { result ->
            _state.value = SettingsReducer.reduce(_state.value, result)
        },
        publish = { label ->
            scope.launch { _labels.emit(label) }
        }
    )

    fun onIntent(intent: SettingsIntent) {
        executor.execute(intent)
    }
}
