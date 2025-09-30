package de.fabianrump.crossnotes.data.mappings

import de.fabianrump.crossnotes.data.remote.dtos.HolidayDto
import de.fabianrump.crossnotes.domain.daos.HolidayData

internal fun HolidayDto.toHolidayData(): HolidayData = HolidayData(
    name = name,
    nameLocal = nameLocal,
    language = language,
    description = description,
    countryCode = countryCode,
    locationName = locationName,
    type = type,
    dateString = dateString,
    dateYear = dateYear,
    dateMonth = dateMonth,
    dateDay = dateDay,
    weekDay = weekDay,
)