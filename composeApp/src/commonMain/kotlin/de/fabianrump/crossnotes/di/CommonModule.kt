package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.data.local.db.AppDatabase
import de.fabianrump.crossnotes.data.local.db.daos.TodoDao
import org.koin.dsl.module

internal val commonModule = module {
    single<TodoDao> { get<AppDatabase>().getTodoDao() }
}