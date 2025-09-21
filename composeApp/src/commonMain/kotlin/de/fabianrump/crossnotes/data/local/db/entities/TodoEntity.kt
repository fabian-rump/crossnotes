package de.fabianrump.crossnotes.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import de.fabianrump.crossnotes.data.local.converter.DateConverter
import de.fabianrump.crossnotes.data.local.converter.PriorityConverter
import de.fabianrump.crossnotes.data.model.Priority
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Entity(tableName = "todos")
@TypeConverters(PriorityConverter::class, DateConverter::class)
internal data class ToDoEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String,
    val priority: Priority,
    val isCompleted: Boolean = false,
    val dueDate: Instant,
    val createdAt: Instant = Clock.System.now()
)