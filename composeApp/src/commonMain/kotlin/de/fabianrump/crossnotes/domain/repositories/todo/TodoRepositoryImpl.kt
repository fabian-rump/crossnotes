package de.fabianrump.crossnotes.domain.repositories.todo

import de.fabianrump.crossnotes.data.local.db.daos.TodoDao
import de.fabianrump.crossnotes.data.local.db.entities.ToDoEntity
import de.fabianrump.crossnotes.data.mappings.toTodoData
import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.mappings.toTodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

internal class TodoRepositoryImpl(
    private val todoDao: TodoDao,
) : TodoRepository {

    override suspend fun insertTodo(todo: TodoData): Long = todoDao.insertToDo(todo = todo.toTodoEntity())

    override suspend fun getAll(): Flow<List<TodoData>> = todoDao.getAll().map { it.map(transform = ToDoEntity::toTodoData) }

    override suspend fun checkTodo(id: Long) {
        todoDao.getToDoById(id = id)
            ?.copy(isCompleted = true)
            ?.let { todoDao.updateToDo(todo = it) }
    }

    override suspend fun uncheckTodo(id: Long) {
        todoDao.getToDoById(id = id)
            ?.copy(isCompleted = false)
            ?.let { todoDao.updateToDo(todo = it) }
    }

    override suspend fun updateDueDate(id: Long, dueDate: LocalDate) {
        todoDao.getToDoById(id = id)
            ?.copy(dueDate = dueDate)
            ?.let { todoDao.updateToDo(todo = it) }
    }
}