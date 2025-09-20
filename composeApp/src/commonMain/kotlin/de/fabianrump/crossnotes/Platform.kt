package de.fabianrump.crossnotes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform