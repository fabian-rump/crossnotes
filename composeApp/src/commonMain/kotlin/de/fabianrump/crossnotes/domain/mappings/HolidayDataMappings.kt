package de.fabianrump.crossnotes.domain.mappings

import de.fabianrump.crossnotes.domain.daos.HolidayData
import de.fabianrump.crossnotes.domain.models.Holiday

internal fun HolidayData.toHoliday(): Holiday = Holiday(
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
    weekDay = weekDay
)