package de.fabianrump.crossnotes.ui.feature.home

import cafe.adriel.voyager.core.model.ScreenModel
import de.fabianrump.crossnotes.domain.repository.UsefulInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class HomeScreenModel(
    private val usefulInfoRepository: UsefulInfoRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(dailyUsefulInfo = usefulInfoRepository.load()) }
    }
}
