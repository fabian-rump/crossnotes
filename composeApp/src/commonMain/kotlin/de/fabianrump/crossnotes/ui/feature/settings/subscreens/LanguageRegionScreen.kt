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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Schedule
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

internal data class Language(
    val code: String,
    val name: String,
    val nativeName: String,
    val flag: String
)

internal data class Region(
    val code: String,
    val name: String,
    val flag: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LanguageRegionScreen(
    onNavigateBack: () -> Unit,
) {
    var selectedLanguage by remember { mutableStateOf(value = "de") }
    var selectedRegion by remember { mutableStateOf(value = "DE") }
    var use24HourFormat by remember { mutableStateOf(value = true) }
    var showLanguageDialog by remember { mutableStateOf(value = false) }
    var showRegionDialog by remember { mutableStateOf(value = false) }
    var selectedDateFormat by remember { mutableStateOf(value = "DD.MM.YYYY") }
    var showDateFormatDialog by remember { mutableStateOf(value = false) }
    var selectedFirstDayOfWeek by remember { mutableStateOf(value = "Montag") }
    var showFirstDayDialog by remember { mutableStateOf(value = false) }

    val languages = listOf(
        Language(code = "de", name = "Deutsch", nativeName = "Deutsch", flag = "🇩🇪"),
        Language(code = "en", name = "Englisch", nativeName = "English", flag = "🇬🇧"),
        Language(code = "fr", name = "Französisch", nativeName = "Français", flag = "🇫🇷"),
        Language(code = "es", name = "Spanisch", nativeName = "Español", flag = "🇪🇸"),
        Language(code = "it", name = "Italienisch", nativeName = "Italiano", flag = "🇮🇹"),
        Language(code = "nl", name = "Niederländisch", nativeName = "Nederlands", flag = "🇳🇱"),
        Language(code = "pl", name = "Polnisch", nativeName = "Polski", flag = "🇵🇱"),
        Language(code = "tr", name = "Türkisch", nativeName = "Türkçe", flag = "🇹🇷")
    )

    val regions = listOf(
        Region(code = "DE", name = "Deutschland", flag = "🇩🇪"),
        Region(code = "AT", name = "Österreich", flag = "🇦🇹"),
        Region(code = "CH", name = "Schweiz", flag = "🇨🇭"),
        Region(code = "GB", name = "Vereinigtes Königreich", flag = "🇬🇧"),
        Region(code = "US", name = "Vereinigte Staaten", flag = "🇺🇸"),
        Region(code = "FR", name = "Frankreich", flag = "🇫🇷"),
        Region(code = "ES", name = "Spanien", flag = "🇪🇸"),
        Region(code = "IT", name = "Italien", flag = "🇮🇹")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sprache & Region") },
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
            LanguageSection(
                selectedLanguage = selectedLanguage,
                languages = languages,
                onLanguageClick = { showLanguageDialog = true }
            )

            RegionSection(
                selectedRegion = selectedRegion,
                regions = regions,
                onRegionClick = { showRegionDialog = true }
            )

            FormatSettingsSection(
                use24HourFormat = use24HourFormat,
                onTimeFormatToggle = { use24HourFormat = it },
                selectedDateFormat = selectedDateFormat,
                onDateFormatClick = { showDateFormatDialog = true },
                selectedFirstDayOfWeek = selectedFirstDayOfWeek,
                onFirstDayClick = { showFirstDayDialog = true }
            )

            CurrencyNumberSection()
        }
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            languages = languages,
            currentSelection = selectedLanguage,
            onDismiss = { showLanguageDialog = false },
            onConfirm = { code ->
                selectedLanguage = code
                showLanguageDialog = false
            }
        )
    }

    if (showRegionDialog) {
        RegionSelectionDialog(
            regions = regions,
            currentSelection = selectedRegion,
            onDismiss = { showRegionDialog = false },
            onConfirm = { code ->
                selectedRegion = code
                showRegionDialog = false
            }
        )
    }

    if (showDateFormatDialog) {
        DateFormatDialog(
            currentSelection = selectedDateFormat,
            onDismiss = { showDateFormatDialog = false },
            onConfirm = { format ->
                selectedDateFormat = format
                showDateFormatDialog = false
            }
        )
    }

    if (showFirstDayDialog) {
        FirstDayOfWeekDialog(
            currentSelection = selectedFirstDayOfWeek,
            onDismiss = { showFirstDayDialog = false },
            onConfirm = { day ->
                selectedFirstDayOfWeek = day
                showFirstDayDialog = false
            }
        )
    }
}

@Composable
private fun LanguageSection(
    selectedLanguage: String,
    languages: List<Language>,
    onLanguageClick: () -> Unit
) {
    val language = languages.find { it.code == selectedLanguage } ?: languages[0]

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.twoAndHalf),
                horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Sprache",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "App-Sprache festlegen",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            Surface(
                onClick = onLanguageClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = MaterialTheme.dimens.twoAndHalf),
                    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = language.flag,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Column(
                        modifier = Modifier.weight(weight = 1f)
                    ) {
                        Text(
                            text = language.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = language.nativeName,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Ändern",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun RegionSection(
    selectedRegion: String,
    regions: List<Region>,
    onRegionClick: () -> Unit
) {
    val region = regions.find { it.code == selectedRegion } ?: regions[0]

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
                        imageVector = Icons.Default.Public,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Region",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Formate und Zeitzone anpassen",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            Surface(
                onClick = onRegionClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = MaterialTheme.dimens.twoAndHalf),
                    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = region.flag,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = region.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(weight = 1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Ändern",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun FormatSettingsSection(
    use24HourFormat: Boolean,
    onTimeFormatToggle: (Boolean) -> Unit,
    selectedDateFormat: String,
    onDateFormatClick: () -> Unit,
    selectedFirstDayOfWeek: String,
    onFirstDayClick: () -> Unit
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
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Format-Einstellungen",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Zeit- und Datumsformate",
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
                Column(
                    modifier = Modifier.weight(weight = 1f).padding(end = MaterialTheme.dimens.two)
                ) {
                    Text(
                        text = "24-Stunden-Format",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = if (use24HourFormat) "14:30" else "2:30 PM",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = use24HourFormat,
                    onCheckedChange = onTimeFormatToggle
                )
            }

            HorizontalDivider()

            Surface(
                onClick = onDateFormatClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = MaterialTheme.dimens.twoAndHalf),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(weight = 1f)
                    ) {
                        Text(
                            text = "Datumsformat",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = selectedDateFormat,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Ändern",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            Surface(
                onClick = onFirstDayClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = MaterialTheme.dimens.twoAndHalf),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(weight = 1f)
                    ) {
                        Text(
                            text = "Erster Wochentag",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = selectedFirstDayOfWeek,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Ändern",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun CurrencyNumberSection() {
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
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Euro,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Währung & Zahlen",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Formate für Zahlen und Währung",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            HorizontalDivider()

            InfoRow(
                label = "Währung",
                value = "Euro (€)",
                icon = Icons.Default.AttachMoney
            )

            HorizontalDivider()

            InfoRow(
                label = "Zahlenformat",
                value = "1.234,56",
                icon = Icons.Default.Pin
            )
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(size = MaterialTheme.dimens.three)
        )
        Column(
            modifier = Modifier.weight(weight = 1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun LanguageSelectionDialog(
    languages: List<Language>,
    currentSelection: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var selectedLanguage by remember { mutableStateOf(value = currentSelection) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Sprache auswählen") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState())
            ) {
                languages.forEach { language ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.one),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf)
                    ) {
                        RadioButton(
                            selected = selectedLanguage == language.code,
                            onClick = { selectedLanguage = language.code }
                        )
                        Text(
                            text = language.flag,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Column {
                            Text(
                                text = language.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = language.nativeName,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedLanguage) }) {
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

@Composable
private fun RegionSelectionDialog(
    regions: List<Region>,
    currentSelection: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var selectedRegion by remember { mutableStateOf(value = currentSelection) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Region auswählen") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(state = rememberScrollState())
            ) {
                regions.forEach { region ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.one),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf)
                    ) {
                        RadioButton(
                            selected = selectedRegion == region.code,
                            onClick = { selectedRegion = region.code }
                        )
                        Text(
                            text = region.flag,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = region.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedRegion) }) {
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

@Composable
private fun DateFormatDialog(
    currentSelection: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val formats = listOf(
        "DD.MM.YYYY" to "30.09.2025",
        "MM/DD/YYYY" to "09/30/2025",
        "YYYY-MM-DD" to "2025-09-30",
        "DD/MM/YYYY" to "30/09/2025"
    )
    var selectedFormat by remember { mutableStateOf(value = currentSelection) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Datumsformat") },
        text = {
            Column {
                formats.forEach { (format, example) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.one),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedFormat == format,
                            onClick = { selectedFormat = format }
                        )
                        Spacer(modifier = Modifier.width(width = MaterialTheme.dimens.one))
                        Column {
                            Text(
                                text = format,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = example,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedFormat) }) {
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

@Composable
private fun FirstDayOfWeekDialog(
    currentSelection: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val days = listOf("Montag", "Sonntag", "Samstag")
    var selectedDay by remember { mutableStateOf(value = currentSelection) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Erster Wochentag") },
        text = {
            Column {
                days.forEach { day ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.one),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedDay == day,
                            onClick = { selectedDay = day }
                        )
                        Spacer(modifier = Modifier.width(width = MaterialTheme.dimens.one))
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedDay) }) {
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