package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.domain.repository.UsefulInfoRepository
import de.fabianrump.crossnotes.domain.repository.UsefulInfoRepositoryImpl
import org.koin.dsl.module

internal val domainRepositoryModule = module {
    single<UsefulInfoRepository> { UsefulInfoRepositoryImpl() }
}