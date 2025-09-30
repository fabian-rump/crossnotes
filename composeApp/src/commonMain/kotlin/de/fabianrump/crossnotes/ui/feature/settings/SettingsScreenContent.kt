package de.fabianrump.crossnotes.ui.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import de.fabianrump.crossnotes.data.model.ThemeMode
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.NavigateToAboutCrossNotes
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.NavigateToPrivacySecurity
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.ToggleNotification
import de.fabianrump.crossnotes.ui.feature.settings.SettingsIntent.ToggleTheme
import de.fabianrump.crossnotes.ui.theme.dimens
import io.github.aakira.napier.Napier

@Composable
internal fun SettingsScreenContent(
    paddingValues: PaddingValues,
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .verticalScroll(state = rememberScrollState())
            .padding(all = MaterialTheme.dimens.two),
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.two)
    ) {
        ThemeSection(
            selectedTheme = state.themeMode,
            onThemeSelected = { onIntent(ToggleTheme(themeMode = it)) }
        )
        NotificationsSection(
            enabled = state.areNotificationsEnabled,
            onToggle = { onIntent(ToggleNotification(isEnabled = it)) }
        )
        OtherSettingsSection(
            onAboutCrossNotesClick = { onIntent(NavigateToAboutCrossNotes) },
            onPrivacySecurityClick = { onIntent(NavigateToPrivacySecurity) }
        )
    }
}

@Composable
private fun ThemeSection(
    selectedTheme: ThemeMode,
    onThemeSelected: (ThemeMode) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Erscheinungsbild",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Theme & Kontrast anpassen",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            Column(
                modifier = Modifier.padding(all = MaterialTheme.dimens.two),
                verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.one)
            ) {
                ThemeMode.entries.forEach { theme ->
                    ThemeOption(
                        theme = theme,
                        isSelected = selectedTheme == theme,
                        onClick = { onThemeSelected(theme) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeOption(
    theme: ThemeMode,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        border = if (isSelected) {
            ButtonDefaults.outlinedButtonBorder
        } else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.dimens.two),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.two),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = theme.emoji,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = theme.displayName,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.weight(weight = 1f)
            )
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Ausgewählt",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun NotificationsSection(
    enabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Benachrichtigungen",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Push-Mitteilungen verwalten",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Benachrichtigungen aktivieren",
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    checked = enabled,
                    onCheckedChange = onToggle
                )
            }
        }
    }
}

@Composable
fun OtherSettingsSection(
    onAboutCrossNotesClick: () -> Unit,
    onPrivacySecurityClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            SettingsItem(
                icon = Icons.Default.Lock,
                title = "Datenschutz & Sicherheit",
                subtitle = "Konto- und Datenschutzeinstellungen",
                iconColor = MaterialTheme.colorScheme.tertiaryContainer,
                iconTint = MaterialTheme.colorScheme.onTertiaryContainer,
                onClick = onPrivacySecurityClick
            )

            HorizontalDivider()

            SettingsItem(
                icon = Icons.Default.Language,
                title = "Sprache & Region",
                subtitle = "Deutsch (Deutschland)",
                iconColor = MaterialTheme.colorScheme.errorContainer,
                iconTint = MaterialTheme.colorScheme.onErrorContainer,
                onClick = {
                    Napier.d { "Sprache & Region Click" }
                }
            )

            HorizontalDivider()

            SettingsItem(
                icon = Icons.Default.Info,
                title = "Über die App",
                subtitle = "Version 1.0.0",
                iconColor = MaterialTheme.colorScheme.primaryContainer,
                iconTint = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = onAboutCrossNotesClick
            )
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.dimens.twoAndHalf),
            horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = iconColor
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                )
            }
            Column(
                modifier = Modifier.weight(weight = 1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Weiter",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}