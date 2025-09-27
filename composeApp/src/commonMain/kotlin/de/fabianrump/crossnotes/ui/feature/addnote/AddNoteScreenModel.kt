package de.fabianrump.crossnotes.ui.feature.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.crossnotes.data.model.Priority
import de.fabianrump.crossnotes.domain.models.Todo
import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

internal class AddNoteScreenModel(
    private val addTodoUseCase: AddTodoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AddNoteScreenState())
    val state = _state.asStateFlow()

    fun onTextChange(text: String) {
        _state.update { it.copy(text = text) }
    }

    fun updateDatePickerVisibility(isVisible: Boolean) {
        _state.update { it.copy(isDatePickerShown = isVisible) }
    }

    fun updateDueDate(date: LocalDate) {
        _state.update { it.copy(dueDate = date) }
    }

    fun updatePriority(priority: Priority) {
        _state.update { it.copy(priority = priority) }
    }

    fun saveTodo() {
        val nonNullDueDate = _state.value.dueDate ?: Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).date
        val todo = Todo(
            id = 0L,
            text = _state.value.text,
            priority = _state.value.priority,
            dueDate = nonNullDueDate,
            isCompleted = false,
        )
        viewModelScope.launch { addTodoUseCase(todo = todo) }
    }
}
