package de.fabianrump.crossnotes.domain.repositories.usefulinfo

internal interface UsefulInfoRepository {
    fun load(): String
}