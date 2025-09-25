package de.fabianrump.crossnotes.domain.repositories.todo

import de.fabianrump.crossnotes.domain.daos.TodoData

internal interface TodoRepository {
    suspend fun insertTodo(todo: TodoData): Long
}