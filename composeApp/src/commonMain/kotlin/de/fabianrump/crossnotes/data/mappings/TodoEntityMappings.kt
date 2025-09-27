package de.fabianrump.crossnotes.data.mappings

import de.fabianrump.crossnotes.data.local.db.entities.ToDoEntity
import de.fabianrump.crossnotes.domain.daos.TodoData

internal fun ToDoEntity.toTodoData(): TodoData = TodoData(
    text = text,
    priority = priority,
    dueDate = dueDate
)