package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainUseCaseModule = module {
    singleOf(::AddTodoUseCase)
}