package de.fabianrump.crossnotes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import crossnotes.composeapp.generated.resources.Res
import crossnotes.composeapp.generated.resources.alata_regular
import crossnotes.composeapp.generated.resources.changa_regular
import org.jetbrains.compose.resources.Font

@Composable
internal fun crossNotesTypography() = Typography().run {
    val displayFontFamily = displayFontFamily()
    val bodyFontFamily = bodyFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = labelSmall.copy(fontFamily = bodyFontFamily),
    )
}

@Composable
private fun displayFontFamily() = FontFamily(
    Font(resource = Res.font.alata_regular, weight = FontWeight.Light),
)

@Composable
private fun bodyFontFamily() = FontFamily(
    Font(resource = Res.font.changa_regular, weight = FontWeight.Light),
)