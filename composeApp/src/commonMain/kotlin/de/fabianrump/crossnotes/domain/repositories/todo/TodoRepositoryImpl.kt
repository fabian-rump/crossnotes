package de.fabianrump.crossnotes.domain.repositories.todo

import de.fabianrump.crossnotes.data.local.db.daos.TodoDao
import de.fabianrump.crossnotes.data.local.db.entities.ToDoEntity
import de.fabianrump.crossnotes.data.mappings.toTodoData
import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.mappings.toTodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TodoRepositoryImpl(
    private val todoDao: TodoDao,
) : TodoRepository {

    override suspend fun insertTodo(todo: TodoData): Long = todoDao.insertToDo(todo = todo.toTodoEntity())

    override suspend fun getAll(): Flow<List<TodoData>> = todoDao.getAll().map { it.map(transform = ToDoEntity::toTodoData) }
}