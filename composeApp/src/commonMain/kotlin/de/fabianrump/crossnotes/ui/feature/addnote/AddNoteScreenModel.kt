package de.fabianrump.crossnotes.ui.feature.addnote

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class AddNoteScreenModel() : ScreenModel {

    private val _state = MutableStateFlow(AddNoteScreenState())
    val state = _state.asStateFlow()

    fun onTextChange(text: String) {
        _state.update { it.copy(text = text) }
    }
}
