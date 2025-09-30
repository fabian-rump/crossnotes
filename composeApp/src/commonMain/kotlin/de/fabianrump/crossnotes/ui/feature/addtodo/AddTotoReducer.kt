package de.fabianrump.crossnotes.ui.feature.addtodo

internal object AddTotoReducer {
    fun reduce(state: AddTodoState, result: AddTodoResult): AddTodoState =
        when (result) {
            is AddTodoResult.Error -> state.copy(error = result.message)
            AddTodoResult.Loading -> state.copy(isLoading = true)
            is AddTodoResult.ChangeDueDate -> state.copy(dueDate = result.date)
            is AddTodoResult.ChangePriority -> state.copy(priority = result.priority)
            is AddTodoResult.ChangeText -> state.copy(text = result.text)
            AddTodoResult.OpenDatePicker -> state.copy(isDatePickerShown = true)
            AddTodoResult.DismissDatePicker -> state.copy(isDatePickerShown = false)
            is AddTodoResult.HolidaysFetched -> state.copy(holidayNames = result.holidays)
        }
}
