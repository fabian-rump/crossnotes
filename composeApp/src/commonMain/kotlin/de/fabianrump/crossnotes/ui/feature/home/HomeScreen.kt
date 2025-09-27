package de.fabianrump.crossnotes.ui.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    onSettingsClick: () -> Unit,
    onFabClick: () -> Unit,
    onPastTodoInfoCardClick: () -> Unit
) {
    val screenModel = koinViewModel<HomeScreenModel>()
    val uiState by screenModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Neue Notiz erstellen"
                )
            }
        }
    ) { innerPadding ->
        HomeScreenContent(
            paddingValues = innerPadding,
            uiState = uiState,
            onSettingsClick = onSettingsClick,
            onPastTodoInfoCardClick = onPastTodoInfoCardClick
        )
    }
}
