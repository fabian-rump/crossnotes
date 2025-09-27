package de.fabianrump.crossnotes.ui.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SettingsScreenContent(
    paddingValues: PaddingValues,
    onNavigateToHistory: () -> Unit
) {
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        ListItem(
            modifier = Modifier.clickable { onNavigateToHistory() },
            headlineContent = { Text(text = "History") },
            supportingContent = { Text(text = "See all completed todos") },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "History"
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Navigate"
                )
            }
        )
    }
}