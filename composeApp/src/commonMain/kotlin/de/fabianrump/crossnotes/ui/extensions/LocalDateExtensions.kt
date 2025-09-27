package de.fabianrump.crossnotes.ui.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

internal fun LocalDate.isInPast(): Boolean = this < todayLocalDate()

internal fun todayLocalDate(): LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

internal fun LocalDate.toReadableText(): String = "$day. ${month.name.toCapitalizeFirstLetter()} $year"

private fun String.toCapitalizeFirstLetter(): String = takeIf { it.isNotEmpty() }
    ?.let { nonEmptyString ->
        val firstChar = nonEmptyString.first().uppercaseChar()
        val rest = nonEmptyString.substring(startIndex = 1).lowercase()
        firstChar + rest
    } ?: ""
