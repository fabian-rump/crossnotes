package de.fabianrump.crossnotes.domain.usecase.todo

import de.fabianrump.crossnotes.domain.daos.TodoData
import de.fabianrump.crossnotes.domain.mappings.toTodo
import de.fabianrump.crossnotes.domain.models.Todo
import de.fabianrump.crossnotes.domain.repositories.todo.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GetHistoryTodosUseCase(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(): Flow<List<Todo>> = todoRepository.getAll().map {
        it.map(transform = TodoData::toTodo)
            .filter { todo -> todo.isCompleted }
            .sortedByDescending(selector = Todo::dueDate)
    }
}
