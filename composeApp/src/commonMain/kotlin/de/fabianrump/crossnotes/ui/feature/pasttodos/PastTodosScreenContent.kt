package de.fabianrump.crossnotes.ui.feature.pasttodos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun PastTodosScreenContent(
    paddingValues: PaddingValues,
    state: PastTodosScreenState
) {
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        state.todos.forEach {
            ListItem(
                headlineContent = { Text(text = it.text) }
            )
        }
    }
}