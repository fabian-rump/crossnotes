package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.data.local.db.AppDatabase
import de.fabianrump.crossnotes.data.local.db.getAppDatabase
import de.fabianrump.crossnotes.data.local.db.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun databaseModule(): Module = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder(context = get())
        getAppDatabase(builder)
    }
}