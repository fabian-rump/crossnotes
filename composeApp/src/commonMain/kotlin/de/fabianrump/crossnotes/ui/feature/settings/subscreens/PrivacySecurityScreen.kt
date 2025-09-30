package de.fabianrump.crossnotes.ui.feature.settings.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.fabianrump.crossnotes.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PrivacySecurityScreen(
    onNavigateBack: () -> Unit
) {
    var biometricEnabled by remember { mutableStateOf(value = false) }
    var autoLockEnabled by remember { mutableStateOf(value = true) }
    var analyticsEnabled by remember { mutableStateOf(value = true) }
    var crashReportsEnabled by remember { mutableStateOf(value = true) }
    var personalizationEnabled by remember { mutableStateOf(value = false) }
    var locationTrackingEnabled by remember { mutableStateOf(value = false) }
    var selectedAutoLockTime by remember { mutableStateOf(value = "5 Minuten") }
    var showAutoLockDialog by remember { mutableStateOf(value = false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Datenschutz & Sicherheit") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Zurück"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .verticalScroll(state = rememberScrollState())
                .padding(all = MaterialTheme.dimens.two),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.two)
        ) {
            SecuritySection(
                biometricEnabled = biometricEnabled,
                onBiometricToggle = { biometricEnabled = it },
                autoLockEnabled = autoLockEnabled,
                onAutoLockToggle = { autoLockEnabled = it },
                selectedAutoLockTime = selectedAutoLockTime,
                onAutoLockTimeClick = { showAutoLockDialog = true }
            )
            PrivacySection(
                analyticsEnabled = analyticsEnabled,
                onAnalyticsToggle = { analyticsEnabled = it },
                crashReportsEnabled = crashReportsEnabled,
                onCrashReportsToggle = { crashReportsEnabled = it },
                personalizationEnabled = personalizationEnabled,
                onPersonalizationToggle = { personalizationEnabled = it },
                locationTrackingEnabled = locationTrackingEnabled,
                onLocationTrackingToggle = { locationTrackingEnabled = it }
            )
            DataManagementSection()
            AccountSecuritySection()
        }
    }

    if (showAutoLockDialog) {
        AutoLockTimeDialog(
            currentSelection = selectedAutoLockTime,
            onDismiss = { showAutoLockDialog = false },
            onConfirm = { time ->
                selectedAutoLockTime = time
                showAutoLockDialog = false
            }
        )
    }
}

@Composable
private fun SecuritySection(
    biometricEnabled: Boolean,
    onBiometricToggle: (Boolean) -> Unit,
    autoLockEnabled: Boolean,
    onAutoLockToggle: (Boolean) -> Unit,
    selectedAutoLockTime: String,
    onAutoLockTimeClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Sicherheit",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Schütze deine Daten",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            ToggleSetting(
                title = "Biometrische Authentifizierung",
                description = "Entsperre die App mit Fingerabdruck oder Gesichtserkennung",
                checked = biometricEnabled,
                onCheckedChange = onBiometricToggle
            )

            HorizontalDivider()

            ToggleSetting(
                title = "Automatische Sperre",
                description = "App nach Inaktivität automatisch sperren",
                checked = autoLockEnabled,
                onCheckedChange = onAutoLockToggle
            )

            if (autoLockEnabled) {
                HorizontalDivider()

                Surface(
                    onClick = onAutoLockTimeClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = MaterialTheme.dimens.twoAndHalf,
                                end = MaterialTheme.dimens.twoAndHalf,
                                top = MaterialTheme.dimens.oneAndHalf,
                                bottom = MaterialTheme.dimens.oneAndHalf
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sperrzeit",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.one),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedAutoLockTime,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(MaterialTheme.dimens.twoAndHalf)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PrivacySection(
    analyticsEnabled: Boolean,
    onAnalyticsToggle: (Boolean) -> Unit,
    crashReportsEnabled: Boolean,
    onCrashReportsToggle: (Boolean) -> Unit,
    personalizationEnabled: Boolean,
    onPersonalizationToggle: (Boolean) -> Unit,
    locationTrackingEnabled: Boolean,
    onLocationTrackingToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.PrivacyTip,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Datenschutz",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Verwalte deine Daten",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            ToggleSetting(
                title = "Nutzungsstatistiken",
                description = "Helfe uns, die App zu verbessern",
                checked = analyticsEnabled,
                onCheckedChange = onAnalyticsToggle
            )

            HorizontalDivider()

            ToggleSetting(
                title = "Absturzberichte",
                description = "Automatisch Fehlerberichte senden",
                checked = crashReportsEnabled,
                onCheckedChange = onCrashReportsToggle
            )

            HorizontalDivider()

            ToggleSetting(
                title = "Personalisierung",
                description = "Inhalte basierend auf deinem Verhalten anpassen",
                checked = personalizationEnabled,
                onCheckedChange = onPersonalizationToggle
            )

            HorizontalDivider()

            ToggleSetting(
                title = "Standortverfolgung",
                description = "Standortdaten für Funktionen verwenden",
                checked = locationTrackingEnabled,
                onCheckedChange = onLocationTrackingToggle
            )
        }
    }
}

@Composable
private fun DataManagementSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Storage,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Datenverwaltung",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Deine gespeicherten Daten",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            ActionItem(
                icon = Icons.Default.Download,
                title = "Daten herunterladen",
                description = "Exportiere alle deine Daten",
                onClick = { }
            )

            HorizontalDivider()

            ActionItem(
                icon = Icons.Default.DeleteForever,
                title = "Cache leeren",
                description = "42,3 MB zwischengespeichert",
                onClick = { }
            )

            HorizontalDivider()

            ActionItem(
                icon = Icons.Default.Delete,
                title = "Alle Daten löschen",
                description = "Unwiderruflich alle App-Daten entfernen",
                onClick = { },
                destructive = true
            )
        }
    }
}

@Composable
private fun AccountSecuritySection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Konto-Sicherheit",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Schütze dein Konto",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            ActionItem(
                icon = Icons.Default.Password,
                title = "Passwort ändern",
                description = "Zuletzt geändert vor 3 Monaten",
                onClick = { }
            )

            HorizontalDivider()

            ActionItem(
                icon = Icons.Default.Devices,
                title = "Aktive Geräte",
                description = "2 Geräte angemeldet",
                onClick = { }
            )

            HorizontalDivider()

            ActionItem(
                icon = Icons.Default.History,
                title = "Anmeldeaktivität",
                description = "Letzte Anmeldung heute um 14:32",
                onClick = { }
            )
        }
    }
}

@Composable
private fun ToggleSetting(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.dimens.twoAndHalf),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(weight = 1f).padding(end = MaterialTheme.dimens.two)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
private fun ActionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit,
    destructive: Boolean = false
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (destructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(size = MaterialTheme.dimens.three)
            )
            Column(
                modifier = Modifier.weight(weight = 1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (destructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Öffnen",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AutoLockTimeDialog(
    currentSelection: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val options = listOf(
        "Sofort",
        "30 Sekunden",
        "1 Minute",
        "5 Minuten",
        "10 Minuten",
        "30 Minuten",
        "Nie"
    )
    var selectedOption by remember { mutableStateOf(value = currentSelection) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Automatische Sperrzeit") },
        text = {
            Column {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.one),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option }
                        )
                        Spacer(modifier = Modifier.width(width = MaterialTheme.dimens.one))
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedOption) }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Abbrechen")
            }
        }
    )
}