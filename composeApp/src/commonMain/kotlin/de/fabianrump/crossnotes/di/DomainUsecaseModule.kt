package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.domain.usecase.info.UsefulInfoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.CheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetAllTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.GetHistoryTodosUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UncheckTodoUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.UpdateDueDateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainUseCaseModule = module {
    singleOf(::AddTodoUseCase)
    singleOf(::GetAllTodosUseCase)
    singleOf(::UsefulInfoUseCase)
    singleOf(::GetHistoryTodosUseCase)
    singleOf(::CheckTodoUseCase)
    singleOf(::UncheckTodoUseCase)
    singleOf(::UpdateDueDateUseCase)
}