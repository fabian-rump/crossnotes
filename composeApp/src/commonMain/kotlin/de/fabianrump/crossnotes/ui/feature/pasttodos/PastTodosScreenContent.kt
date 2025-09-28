package de.fabianrump.crossnotes.ui.feature.pasttodos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.fabianrump.crossnotes.ui.extensions.toReadableText

@Composable
internal fun PastTodosScreenContent(
    paddingValues: PaddingValues,
    state: PastTodosState,
    onIntent: (intent: PastTodosIntent) -> Unit,
) {
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        state.todos.forEach {
            ListItem(
                leadingContent = {
                    Checkbox(
                        checked = it.isCompleted,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                onIntent(PastTodosIntent.CheckTodo(id = it.id))
                            }
                        },
                    )
                },
                headlineContent = { Text(text = it.text) },
                supportingContent = { Text(text = it.dueDate.toReadableText()) },
                trailingContent = {
                    IconButton(
                        onClick = {
                            onIntent(PastTodosIntent.OpenDatePicker(id = it.id, date = it.dueDate))
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    )
                }
            )
        }
    }
}