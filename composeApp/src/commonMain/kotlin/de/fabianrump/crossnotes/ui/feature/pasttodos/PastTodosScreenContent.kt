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
import kotlinx.datetime.LocalDate

@Composable
internal fun PastTodosScreenContent(
    paddingValues: PaddingValues,
    state: PastTodosScreenState,
    onCheckedTodo: (todoId: Long) -> Unit,
    onEditTodo: (todoId: Long, dueDate: LocalDate) -> Unit
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
                                onCheckedTodo(it.id)
                            }
                        },
                    )
                },
                headlineContent = { Text(text = it.text) },
                supportingContent = { Text(text = it.dueDate.toReadableText()) },
                trailingContent = {
                    IconButton(
                        onClick = {
                            onEditTodo(it.id, it.dueDate)
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