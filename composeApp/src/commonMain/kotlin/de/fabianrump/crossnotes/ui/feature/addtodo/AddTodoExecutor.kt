package de.fabianrump.crossnotes.ui.feature.addtodo

import de.fabianrump.crossnotes.data.model.Priority
import de.fabianrump.crossnotes.domain.models.Todo
import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoLabel.NavigateBack
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoLabel.ShowErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.ChangeDueDate
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.ChangePriority
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.ChangeText
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.DismissDatePicker
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.Error
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.OpenDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

internal class AddTodoExecutor(
    private val addTodoUseCase: AddTodoUseCase,
    private val scope: CoroutineScope,
    private val dispatch: (AddTodoResult) -> Unit,
    private val publish: (AddTodoLabel) -> Unit
) {
    fun execute(intent: AddTodoIntent) {
        when (intent) {
            is AddTodoIntent.SaveTodo -> saveTodo(
                text = intent.text,
                priority = intent.priority,
                dueDate = intent.dueDate
            )

            is AddTodoIntent.ChangeDueDate -> changeDueDate(date = intent.date)
            is AddTodoIntent.ChangePriority -> changePriority(priority = intent.priority)
            is AddTodoIntent.ChangeText -> changeText(text = intent.text)
            AddTodoIntent.OpenDatePicker -> openDatePicker()
            AddTodoIntent.DismissDatePicker -> dismissDatePicker()
        }
    }

    private fun dismissDatePicker() {
        dispatch(DismissDatePicker)
    }

    private fun openDatePicker() {
        dispatch(OpenDatePicker)
    }

    private fun changeText(text: String) {
        dispatch(ChangeText(text = text))
    }

    private fun changePriority(priority: Priority) {
        dispatch(ChangePriority(priority = priority))
    }

    private fun changeDueDate(date: LocalDate) {
        dispatch(ChangeDueDate(date = date))
    }

    private fun saveTodo(
        text: String,
        priority: Priority,
        dueDate: LocalDate
    ) {
        scope.launch {
            dispatch(AddTodoResult.Loading)
            try {
                val todo = Todo(
                    id = 0L,
                    text = text,
                    priority = priority,
                    dueDate = dueDate,
                    isCompleted = false,
                )
                addTodoUseCase(todo = todo)
                publish(NavigateBack)
            } catch (e: Exception) {
                dispatch(Error(message = e.message ?: "Unknown error"))
                publish(ShowErrorSnackbar(message = "Failed to load notes"))
            }
        }
    }
}
