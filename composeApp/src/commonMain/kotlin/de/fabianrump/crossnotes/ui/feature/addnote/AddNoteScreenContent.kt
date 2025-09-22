package de.fabianrump.crossnotes.ui.feature.addnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.fabianrump.crossnotes.data.model.Priority
import de.fabianrump.crossnotes.data.model.Priority.HIGH
import de.fabianrump.crossnotes.data.model.Priority.LOW
import de.fabianrump.crossnotes.data.model.Priority.MEDIUM
import de.fabianrump.crossnotes.ui.theme.dimens
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun AddNoteScreenContent(
    paddingValues: PaddingValues,
    state: AddNoteScreenState,
    onTextChange: (String) -> Unit,
    onDueDateClick: () -> Unit,
    onCreateTodoClick: () -> Unit,
    onPriorityChange: (Priority) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = MaterialTheme.dimens.two)
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Text("What needs to be done?", style = MaterialTheme.typography.labelLarge)
        TodoText(
            state = state,
            onTextChange = onTextChange
        )
        Text("Priority Level", style = MaterialTheme.typography.labelLarge)
        PriorityRow(priority = state.priority, onPriorityChange = onPriorityChange)
        Text("Due Date", style = MaterialTheme.typography.labelLarge)
        DueDate(onDueDateClick = onDueDateClick, dueDate = state.dueDate)
        Button(
            onClick = onCreateTodoClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.two),
            content = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add"
                        )
                        Spacer(modifier = Modifier.width(width = MaterialTheme.dimens.one))
                        Text(
                            text = "Create Todo", style = MaterialTheme.typography.labelLarge
                        )
                    }
                )
            },
        )
    }
}

@Composable
private fun DueDate(onDueDateClick: () -> Unit, dueDate: LocalDate?) {
    Card(
        onClick = onDueDateClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.dimens.two),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.dimens.defaultElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.dimens.two),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = {
                Text(text = dueDate?.toFormattedString() ?: "DD.MM.YYYY")
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    modifier = Modifier.size(size = 36.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        )
    }
}

@Composable
private fun PriorityRow(
    priority: Priority,
    onPriorityChange: (Priority) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PriorityCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.Flag,
            iconContentDescription = "Low",
            text = "Low",
            iconColor = Color(0xFF059669),
            isSelected = priority == LOW,
            onClick = {
                onPriorityChange(LOW)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        PriorityCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.Flag,
            iconContentDescription = "Medium",
            text = "Medium",
            iconColor = Color(0xFFD97706),
            isSelected = priority == MEDIUM,
            onClick = {
                onPriorityChange(MEDIUM)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        PriorityCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.Flag,
            iconContentDescription = "High",
            text = "High",
            iconColor = Color(0xFFDC2626),
            isSelected = priority == HIGH,
            onClick = {
                onPriorityChange(HIGH)
            }
        )
    }
}

@Composable
private fun PriorityCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconContentDescription: String,
    iconColor: Color,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                modifier = Modifier.size(36.dp),
                tint = iconColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
private fun TodoText(state: AddNoteScreenState, onTextChange: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = MaterialTheme.dimens.two),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.dimens.defaultElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        TextField(
            value = state.text,
            onValueChange = onTextChange,
            label = { Text(text = "Enter your todo...") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        )
    }
}

@Composable
@Preview
private fun AddNoteScreenContentPreview() {
    AddNoteScreenContent(
        paddingValues = PaddingValues(),
        state = AddNoteScreenState(),
        onTextChange = {},
        onDueDateClick = {},
        onCreateTodoClick = {},
        onPriorityChange = {},
    )
}

private fun LocalDate.toFormattedString(): String {
    val day = this.day.toString().padStart(length = 2, padChar = '0')
    val month = this.month.number.toString().padStart(length = 2, padChar = '0')
    val year = this.year.toString()
    return "$day.$month.$year"
}