package de.fabianrump.crossnotes.data.local.converter

import androidx.room.TypeConverter
import de.fabianrump.crossnotes.data.model.Priority
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority?): String? {
        return priority?.name
    }

    @TypeConverter
    fun toPriority(priorityString: String?): Priority? {
        return priorityString?.let { Priority.valueOf(it) }
    }
}

class DateConverter {
    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? {
        return date?.toEpochMilliseconds()
    }
}