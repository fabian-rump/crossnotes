package de.fabianrump.crossnotes.ui.feature.settings.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.fabianrump.crossnotes.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AboutCrossNotesScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Über die App") },
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
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(MaterialTheme.dimens.two),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.two),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(height = MaterialTheme.dimens.two))
            AppHeaderSection()
            Spacer(modifier = Modifier.height(height = MaterialTheme.dimens.one))
            VersionInfoCard()
            AppDetailsCard()
            DeveloperCard()
            LegalLinksCard()
            CreditsCard()
            Spacer(modifier = Modifier.height(height = MaterialTheme.dimens.two))
        }
    }
}

@Composable
fun AppHeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf)
    ) {
        Surface(
            modifier = Modifier.size(size = 100.dp),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = "App Icon",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(all = MaterialTheme.dimens.three)
            )
        }

        Text(
            text = "CrossNotes",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Deine persönliche ToDo-App",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun VersionInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.dimens.twoAndHalf),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Version",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "1.0.0 (Build 100)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Surface(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Text(
                    text = "Aktuell",
                    modifier = Modifier.padding(horizontal = MaterialTheme.dimens.two, vertical = MaterialTheme.dimens.one),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Composable
fun AppDetailsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            Text(
                text = "App-Details",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(all = MaterialTheme.dimens.twoAndHalf).padding(bottom = MaterialTheme.dimens.paddingSmall)
            )

            HorizontalDivider()

            DetailRow(
                icon = Icons.Default.CalendarToday,
                label = "Veröffentlicht",
                value = "September 2025"
            )

            HorizontalDivider()

            DetailRow(
                icon = Icons.Default.Phone,
                label = "Anforderung",
                value = "Android 8.0+"
            )

            HorizontalDivider()

            DetailRow(
                icon = Icons.Default.Storage,
                label = "Größe",
                value = "12,5 MB"
            )

            HorizontalDivider()

            DetailRow(
                icon = Icons.Default.Download,
                label = "Downloads",
                value = "1.000+"
            )
        }
    }
}

@Composable
fun DeveloperCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier.padding(all = MaterialTheme.dimens.twoAndHalf),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.two)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Code,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(all = MaterialTheme.dimens.oneAndHalf)
                    )
                }
                Column {
                    Text(
                        text = "Entwickler",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Dein Entwicklername",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = "Entwickelt mit ❤️ in Deutschland. Diese App wurde mit modernsten Android-Technologien und Material Design 3 erstellt.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun LegalLinksCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column {
            LinkItem(
                icon = Icons.Default.Description,
                title = "Datenschutzerklärung",
                onClick = { }
            )

            HorizontalDivider()

            LinkItem(
                icon = Icons.Default.Gavel,
                title = "Nutzungsbedingungen",
                onClick = { }
            )

            HorizontalDivider()

            LinkItem(
                icon = Icons.Default.Security,
                title = "Lizenzen",
                onClick = { }
            )
        }
    }
}

@Composable
fun CreditsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier.padding(all = MaterialTheme.dimens.twoAndHalf),
            verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.oneAndHalf)
        ) {
            Text(
                text = "Danksagungen",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Diese App nutzt folgende Open-Source-Bibliotheken:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimens.one)
            ) {
                CreditItem(name = "Jetpack Compose", author = "Google")
                CreditItem(name = "Material Design 3", author = "Google")
                CreditItem(name = "Kotlin", author = "JetBrains")
            }
        }
    }
}

@Composable
fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.twoAndHalf),
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
fun LinkItem(
    icon: ImageVector,
    title: String,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(size = MaterialTheme.dimens.three)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(weight = 1f)
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Öffnen",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun CreditItem(name: String, author: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "• $name",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = author,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}