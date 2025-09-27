package de.fabianrump.crossnotes.domain.mappings

import de.fabianrump.crossnotes.data.local.db.entities.ToDoEntity
import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.models.Todo

internal fun TodoData.toTodoEntity(): ToDoEntity = ToDoEntity(
    id = id,
    text = text,
    priority = priority,
    dueDate = dueDate,
    isCompleted = isCompleted
)

internal fun TodoData.toTodo(): Todo = Todo(
    id = id,
    text = text,
    priority = priority,
    dueDate = dueDate,
    isCompleted = isCompleted,
)
