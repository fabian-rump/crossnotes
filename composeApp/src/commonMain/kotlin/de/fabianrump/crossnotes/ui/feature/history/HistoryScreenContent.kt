package de.fabianrump.crossnotes.ui.feature.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.fabianrump.crossnotes.ui.extensions.toReadableText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HistoryScreenContent(
    paddingValues: PaddingValues,
    state: HistoryState
) {
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        state.todos.forEach {
            ListItem(
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check"
                    )
                },
                headlineContent = { Text(text = it.text) },
                supportingContent = { Text(text = it.dueDate.toReadableText()) }
            )
        }
    }
}

@Preview
@Composable
private fun HistoryScreenContentPreview() {
    HistoryScreenContent(paddingValues = PaddingValues(), state = HistoryState())
}