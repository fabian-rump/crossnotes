package de.fabianrump.crossnotes.domain.models

import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate

internal data class Todo(
    val id: Long,
    val text: String,
    val priority: Priority,
    val dueDate: LocalDate,
    val isCompleted: Boolean
)