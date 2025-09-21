package de.fabianrump.crossnotes.util

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate

@Composable
expect fun DatePickerDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    initialDate: LocalDate? = null
)