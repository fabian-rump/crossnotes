package de.fabianrump.crossnotes.domain.repository

internal interface UsefulInfoRepository {
    fun load(): String
}