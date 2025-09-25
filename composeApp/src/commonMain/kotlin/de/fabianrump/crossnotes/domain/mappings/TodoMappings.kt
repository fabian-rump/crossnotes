package de.fabianrump.crossnotes.domain.mappings

import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.models.Todo

internal fun Todo.toTodoData(): TodoData = TodoData(
    text = text,
    priority = priority,
    dueDate = dueDate
)