package de.fabianrump.crossnotes.domain.repositories.holidays

import arrow.core.raise.Raise
import de.fabianrump.crossnotes.data.remote.model.HolidayError
import de.fabianrump.crossnotes.domain.daos.HolidayData

internal interface HolidayRepository {

    suspend fun Raise<HolidayError>.fetchHolidays(year: Int, month: Int, day: Int): List<HolidayData>
}