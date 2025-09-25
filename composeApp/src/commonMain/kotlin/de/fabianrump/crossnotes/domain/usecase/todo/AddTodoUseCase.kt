package de.fabianrump.crossnotes.domain.usecase.todo

import de.fabianrump.crossnotes.domain.mappings.toTodoData
import de.fabianrump.crossnotes.domain.models.Todo
import de.fabianrump.crossnotes.domain.repositories.todo.TodoRepository

internal class AddTodoUseCase(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(todo: Todo): Long = todoRepository.insertTodo(todo = todo.toTodoData())
}