package de.fabianrump.crossnotes.di

import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoStore
import de.fabianrump.crossnotes.ui.feature.history.HistoryStore
import de.fabianrump.crossnotes.ui.feature.home.HomeStore
import de.fabianrump.crossnotes.ui.feature.pasttodos.PastTodosStore
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

internal val uiModule = module {
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

    factory { (scope: CoroutineScope) ->
        PastTodosStore(
            getAllTodosUseCase = get(),
            checkTodoUseCase = get(),
            uncheckTodoUseCase = get(),
            updateDueDateUseCase = get(),
            scope = scope,
        )
    }

    factory { (scope: CoroutineScope) ->
        AddTodoStore(
            addTodoUseCase = get(),
            scope = scope,
        )
    }
}