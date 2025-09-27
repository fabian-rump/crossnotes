package de.fabianrump.crossnotes.domain.usecase.todo

import de.fabianrump.crossnotes.domain.repositories.todo.TodoRepository
import kotlinx.datetime.LocalDate

internal class UpdateDueDateUseCase(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(id: Long, dueDate: LocalDate) {
        todoRepository.updateDueDate(id = id, dueDate = dueDate)
    }
}