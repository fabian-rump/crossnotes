package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.data.remote.api.HolidayApi
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val networkModule = module {
    factoryOf(::HolidayApi)
}