package de.fabianrump.crossnotes.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import java.util.Calendar
import android.app.DatePickerDialog as AndroidDatePickerDialog
import android.widget.DatePicker as AndroidDatePickerView

@Composable
actual fun DatePickerDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    initialDate: LocalDate?
) {
    val context = LocalContext.current

    val calendar = remember { Calendar.getInstance() }
    initialDate?.let {
        calendar.set(it.year, it.monthNumber - 1, it.dayOfMonth)
    }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    if (show) {
        LaunchedEffect(Unit) {
            val datePickerDialog = AndroidDatePickerDialog(
                context,
                { _: AndroidDatePickerView, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                    val monthEnum = Month(selectedMonth + 1)
                    onDateSelected(LocalDate(selectedYear, monthEnum, selectedDayOfMonth))
                },
                year,
                month,
                day
            )
            datePickerDialog.setOnDismissListener {
                onDismissRequest()
            }
            datePickerDialog.show()
        }
    }
}
