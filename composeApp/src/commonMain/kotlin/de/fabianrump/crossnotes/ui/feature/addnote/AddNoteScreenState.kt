package de.fabianrump.crossnotes.ui.feature.addnote

import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate

internal data class AddNoteScreenState(
    val text: String = "",
    val dueDate: LocalDate? = null,
    val isDatePickerShown: Boolean = false,
    val priority: Priority = Priority.LOW
)
