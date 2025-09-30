package de.fabianrump.crossnotes.domain.daos

internal class HolidayData(
    val name: String,
    val nameLocal: String?,
    val language: String?,
    val description: String?,
    val countryCode: String,
    val locationName: String,
    val type: String,
    val dateString: String,
    val dateYear: String,
    val dateMonth: String,
    val dateDay: String,
    val weekDay: String
)