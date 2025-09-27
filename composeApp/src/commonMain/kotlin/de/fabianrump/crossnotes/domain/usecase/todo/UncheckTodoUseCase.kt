package de.fabianrump.crossnotes.domain.usecase.todo

import de.fabianrump.crossnotes.domain.repositories.todo.TodoRepository

internal class UncheckTodoUseCase(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(id: Long) {
        todoRepository.uncheckTodo(id = id)
    }
}