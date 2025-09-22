package de.fabianrump.crossnotes.ui.feature.addnote

import cafe.adriel.voyager.core.model.ScreenModel
import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

internal class AddNoteScreenModel() : ScreenModel {

    private val _state = MutableStateFlow(AddNoteScreenState())
    val state = _state.asStateFlow()

    fun onTextChange(text: String) {
        _state.update { it.copy(text = text) }
    }

    fun updateDatePickerVisibility(isVisible: Boolean) {
        _state.update { it.copy(isDatePickerShown = isVisible) }
    }

    fun updateDueDate(date: LocalDate?) {
        _state.update { it.copy(dueDate = date) }
    }

    fun updatePriority(priority: Priority) {
        _state.update { it.copy(priority = priority) }
    }
}
