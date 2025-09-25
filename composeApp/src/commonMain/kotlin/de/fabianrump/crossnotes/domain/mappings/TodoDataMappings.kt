package de.fabianrump.crossnotes.domain.mappings

import de.fabianrump.crossnotes.data.local.db.entities.ToDoEntity
import de.fabianrump.crossnotes.domain.daos.TodoData

internal fun TodoData.toTodoEntity(): ToDoEntity = ToDoEntity(
    text = text,
    priority = priority,
    dueDate = dueDate,
)