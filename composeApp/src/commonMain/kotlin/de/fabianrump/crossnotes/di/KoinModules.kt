package de.fabianrump.crossnotes.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

internal fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            commonModule,
            uiModule,
            domainRepositoryModule,
            domainUseCaseModule,
            commonModule,
            databaseModule()
        )
    }
}

fun initKoinIos() = initKoin(appDeclaration = {})