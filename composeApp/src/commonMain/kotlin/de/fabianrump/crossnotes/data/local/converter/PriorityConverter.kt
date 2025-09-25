package de.fabianrump.crossnotes.data.local.converter

import androidx.room.TypeConverter
import de.fabianrump.crossnotes.data.model.Priority
import kotlinx.datetime.LocalDate
import kotlin.time.ExperimentalTime

internal class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority?): String? {
        return priority?.name
    }

    @TypeConverter
    fun toPriority(priorityString: String?): Priority? {
        return priorityString?.let { Priority.valueOf(value = it) }
    }
}

class DateConverter {
    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.fromEpochDays(epochDays = it) }
    }

    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDays()
    }
}