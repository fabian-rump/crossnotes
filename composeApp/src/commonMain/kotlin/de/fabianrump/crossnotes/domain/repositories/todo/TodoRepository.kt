package de.fabianrump.crossnotes.domain.repositories.todo

import de.fabianrump.crossnotes.domain.daos.TodoData
import kotlinx.coroutines.flow.Flow

internal interface TodoRepository {
    suspend fun insertTodo(todo: TodoData): Long

    suspend fun getAll(): Flow<List<TodoData>>
}