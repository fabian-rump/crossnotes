package de.fabianrump.crossnotes.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import de.fabianrump.crossnotes.ui.theme.dimens
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeScreenContent(
    paddingValues: PaddingValues,
    uiState: HomeScreenUiState
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        Header()
        InfoCard(
            usefulInfo = uiState.dailyUsefulInfo ?: "",
        )
    }
}

@Composable
private fun InfoCard(
    usefulInfo: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.dimens.two),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.dimens.defaultElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.dimens.paddingLarge),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.Lightbulb,
                contentDescription = "iconContentDescription",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.paddingMedium))
            Text(
                text = usefulInfo,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.dimens.one,
                horizontal = MaterialTheme.dimens.two
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text("Today", style = MaterialTheme.typography.displayLarge, fontSize = MaterialTheme.dimens.textLarge)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(width = MaterialTheme.dimens.seven)
                    .height(height = MaterialTheme.dimens.five)
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    )
}

@Composable
@Preview
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        paddingValues = PaddingValues(),
        uiState = HomeScreenUiState(
            dailyUsefulInfo = "Meine To-Do-Liste hat auch eine To-Do-Liste."
        )
    )
}