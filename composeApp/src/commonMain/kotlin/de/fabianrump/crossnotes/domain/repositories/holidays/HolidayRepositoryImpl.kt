package de.fabianrump.crossnotes.domain.repositories.holidays

import arrow.core.raise.Raise
import de.fabianrump.crossnotes.data.mappings.toHolidayData
import de.fabianrump.crossnotes.data.remote.api.HolidayApi
import de.fabianrump.crossnotes.data.remote.model.HolidayError
import de.fabianrump.crossnotes.domain.daos.HolidayData

internal class HolidayRepositoryImpl(
    private val holidayApi: HolidayApi
) : HolidayRepository {

    override suspend fun Raise<HolidayError>.fetchHolidays(
        year: Int,
        month: Int,
        day: Int
    ): List<HolidayData> {
        with(holidayApi) {
            fetchHolidays(
                year = year,
                month = month,
                day = day
            ).apply {
                return map { it.toHolidayData() }
            }
        }
    }
}