package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.ui.feature.addnote.AddNoteScreenModel
import de.fabianrump.crossnotes.ui.feature.history.HistoryScreenModel
import de.fabianrump.crossnotes.ui.feature.home.HomeStore
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosScreenModel
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val uiModule = module {
    factoryOf(::AddNoteScreenModel)
    factoryOf(::PastTodosScreenModel)
    factoryOf(::HistoryScreenModel)

    factory { (scope: CoroutineScope) ->
        HomeStore(
            usefulInfoUseCase = get(),
            getAllTodosUseCase = get(),
            checkTodoUseCase = get(),
            uncheckTodoUseCase = get(),
            scope = scope,
        )
    }
}