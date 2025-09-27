package de.fabianrump.crossnotes.domain.mappings

import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.models.Todo

internal fun Todo.toTodoData(): TodoData = TodoData(
    id = id,
    text = text,
    priority = priority,
    dueDate = dueDate,
    isCompleted = isCompleted,
)