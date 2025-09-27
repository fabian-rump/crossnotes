package de.fabianrump.crossnotes.ui.feature.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HistoryScreenContent(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        Text("History")
    }
}

@Preview
@Composable
private fun HistoryScreenContentPreview() {
    HistoryScreenContent(paddingValues = PaddingValues())
}