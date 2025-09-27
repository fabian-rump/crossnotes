package de.fabianrump.crossnotes.domain.repositories.todo

import de.fabianrump.crossnotes.domain.daos.TodoData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

internal interface TodoRepository {
    suspend fun insertTodo(todo: TodoData): Long

    suspend fun getAll(): Flow<List<TodoData>>

    suspend fun checkTodo(id: Long)
    suspend fun uncheckTodo(id: Long)
    suspend fun updateDueDate(id: Long, dueDate: LocalDate)
}