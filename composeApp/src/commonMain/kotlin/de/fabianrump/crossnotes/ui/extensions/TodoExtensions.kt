package de.fabianrump.crossnotes.ui.extensions

import de.fabianrump.crossnotes.domain.models.Todo

internal fun Todo.isInPast(): Boolean = dueDate.isInPast()
