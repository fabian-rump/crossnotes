package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.domain.repositories.todo.TodoRepository
import de.fabianrump.crossnotes.domain.repositories.todo.TodoRepositoryImpl
import de.fabianrump.crossnotes.domain.repositories.usefulinfo.UsefulInfoRepository
import de.fabianrump.crossnotes.domain.repositories.usefulinfo.UsefulInfoRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val domainRepositoryModule = module {
    singleOf(::UsefulInfoRepositoryImpl).bind(UsefulInfoRepository::class)
    singleOf(::TodoRepositoryImpl).bind(TodoRepository::class)
}