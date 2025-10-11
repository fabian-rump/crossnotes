package de.fabianrump.crossnotes.ui.feature.addtodo

import arrow.core.raise.either
import arrow.core.raise.withError
import crossnotes.composeapp.generated.resources.Res
import crossnotes.composeapp.generated.resources.add_todo_failed_to_load_holidays
import crossnotes.composeapp.generated.resources.add_todo_failed_to_save_todo
import crossnotes.composeapp.generated.resources.common_unknown_error
import de.fabianrump.crossnotes.data.model.Priority
import de.fabianrump.crossnotes.data.remote.model.HolidayError
import de.fabianrump.crossnotes.domain.models.Holiday
import de.fabianrump.crossnotes.domain.models.Todo
import de.fabianrump.crossnotes.domain.usecase.holidays.FetchHolidaysUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoLabel.NavigateBack
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoLabel.ShowErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.ChangeDueDate
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.ChangePriority
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.ChangeText
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.DismissDatePicker
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.Error
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.OpenDatePicker
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import org.jetbrains.compose.resources.getString

internal class AddTodoExecutor(
    private val addTodoUseCase: AddTodoUseCase,
    private val fetchHolidaysUseCase: FetchHolidaysUseCase,
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

    private fun fetchHolidays(date: LocalDate) {
        scope.launch {
            either<HolidayError, List<Holiday>> {
                withError(transform = { HolidayError.GeneralError(it.message) }) {
                    with(receiver = fetchHolidaysUseCase) {
                        invoke(year = date.year, month = date.month.number, day = date.day)
                    }
                }
            }.fold(
                ifLeft = {
                    Napier.d { "fetchHolidays Error: $it" }
                    dispatch(Error(message = it.message))
                    publish(ShowErrorSnackbar(message = getString(resource = Res.string.add_todo_failed_to_load_holidays)))
                },
                ifRight = { holidays ->
                    dispatch(AddTodoResult.HolidaysFetched(holidays = holidays.map { it.name }))
                    Napier.d { "fetchHolidays: ${holidays.map { it.name }}" }
                }
            )
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
        fetchHolidays(date = date)
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
                dispatch(Error(message = e.message ?: getString(resource = Res.string.common_unknown_error)))
                publish(ShowErrorSnackbar(message = getString(resource = Res.string.add_todo_failed_to_save_todo)))
            }
        }
    }
}
