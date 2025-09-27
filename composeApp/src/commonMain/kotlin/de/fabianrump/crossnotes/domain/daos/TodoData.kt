package de.fabianrump.crossnotes.domain.daos

import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate

internal data class TodoData(
    val id: Long,
    val text: String,
    val priority: Priority,
    val dueDate: LocalDate,
    val isCompleted: Boolean
)