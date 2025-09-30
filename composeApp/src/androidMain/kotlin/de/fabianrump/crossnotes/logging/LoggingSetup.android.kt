package de.fabianrump.crossnotes.logging

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual fun setupLogging() {
    Napier.base(antilog = DebugAntilog())
}