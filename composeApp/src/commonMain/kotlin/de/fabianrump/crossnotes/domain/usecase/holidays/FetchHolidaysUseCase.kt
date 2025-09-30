package de.fabianrump.crossnotes.domain.usecase.holidays

import arrow.core.raise.Raise
import de.fabianrump.crossnotes.data.remote.model.HolidayError
import de.fabianrump.crossnotes.domain.mappings.toHoliday
import de.fabianrump.crossnotes.domain.models.Holiday
import de.fabianrump.crossnotes.domain.repositories.holidays.HolidayRepository

internal class FetchHolidaysUseCase(
    private val holidayRepository: HolidayRepository
) {

    suspend operator fun Raise<HolidayError>.invoke(year: Int, month: Int, day: Int): List<Holiday> {
        with(holidayRepository) {
            return fetchHolidays(
                year = year,
                month = month,
                day = day
            ).map { it.toHoliday() }
        }
    }
}