package de.fabianrump.crossnotes.domain.repositories.todo

import de.fabianrump.crossnotes.data.local.db.daos.TodoDao
import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.mappings.toTodoEntity

internal class TodoRepositoryImpl(
    private val todoDao: TodoDao,
) : TodoRepository {

    override suspend fun insertTodo(todo: TodoData): Long = todoDao.insertToDo(todo = todo.toTodoEntity())
}