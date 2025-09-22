package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.ui.feature.addnote.AddNoteScreenModel
import de.fabianrump.crossnotes.ui.feature.home.HomeScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val uiModule = module {
    factoryOf(::HomeScreenModel)
    factoryOf(::AddNoteScreenModel)
}