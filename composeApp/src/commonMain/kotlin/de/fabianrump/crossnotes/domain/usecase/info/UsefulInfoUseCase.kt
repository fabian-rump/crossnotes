package de.fabianrump.crossnotes.domain.usecase.info

import de.fabianrump.crossnotes.domain.repositories.usefulinfo.UsefulInfoRepository

internal class UsefulInfoUseCase(
    private val usefulInfoRepository: UsefulInfoRepository
) {

    operator fun invoke(): String = usefulInfoRepository.load()
}