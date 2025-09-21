package de.fabianrump.crossnotes.ui.feature.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.fabianrump.crossnotes.ui.theme.dimens
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeScreenContent(
    paddingValues: PaddingValues,
    uiState: HomeScreenState,
    onSettingsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
    ) {
        Header(onSettingsClick = onSettingsClick)
        InfoCard(
            usefulInfo = uiState.dailyUsefulInfo ?: "",
        )
        AnimatedVisibility(visible = uiState.pastTodosExists) {
            PastTodoInfoCard()
        }
        PriorityContainer(
            headline = "High Priority",
            items = listOf("Item 1", "Item 2", "Item 3")
        )
        PriorityContainer(
            headline = "Medium Priority",
            items = listOf("Item 1", "Item 2")
        )
        PriorityContainer(
            headline = "Low Priority",
            items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        )
    }
}

@Composable
private fun PriorityContainer(
    headline: String,
    items: List<String>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.dimens.two),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.dimens.defaultElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(all = MaterialTheme.dimens.two),
                text = headline,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            items.forEach {
                ListItem(
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent
                    ),
                    headlineContent = { Text(it, style = MaterialTheme.typography.labelLarge) },
                    leadingContent = {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {}
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun PastTodoInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.dimens.two),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.dimens.defaultElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondaryContainer),
        content = {
            Row(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.paddingLarge),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.WarningAmber,
                    contentDescription = "iconContentDescription",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.paddingMedium))
                Text(
                    text = "There are still open todos from tomorrow or later. Would you like to mark them as completed or change the due date?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    )
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiaryContainer)
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
                tint = MaterialTheme.colorScheme.tertiaryContainer
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.paddingMedium))
            Text(
                text = usefulInfo,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
private fun Header(
    onSettingsClick: () -> Unit
) {
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
                    onClick = onSettingsClick,
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
        uiState = HomeScreenState(
            dailyUsefulInfo = "Meine To-Do-Liste hat auch eine To-Do-Liste."
        ),
        onSettingsClick = {}
    )
}