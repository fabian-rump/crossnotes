package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.ui.feature.addnote.AddNoteScreenModel
import de.fabianrump.crossnotes.ui.feature.history.HistoryStore
import de.fabianrump.crossnotes.ui.feature.home.HomeStore
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosScreenModel
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val uiModule = module {
    factoryOf(::AddNoteScreenModel)
    factoryOf(::PastTodosScreenModel)

    factory { (scope: CoroutineScope) ->
        HomeStore(
            usefulInfoUseCase = get(),
            getAllTodosUseCase = get(),
            checkTodoUseCase = get(),
            uncheckTodoUseCase = get(),
            scope = scope,
        )
    }

    factory { (scope: CoroutineScope) ->
        HistoryStore(
            getHistoryTodosUseCase = get(),
            scope = scope,
        )
    }
}