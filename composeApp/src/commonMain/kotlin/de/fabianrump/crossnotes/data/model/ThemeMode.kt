package de.fabianrump.crossnotes.data.model

internal enum class ThemeMode(val displayName: String, val emoji: String) {
    LIGHT("Hell", "☀️"),
    LIGHT_MEDIUM("Hell Medium Kontrast", "🌤️"),
    LIGHT_HIGH("Hell Hoher Konstrast", "🌞"),
    DARK("Dunkel", "🌙"),
    DARK_MEDIUM("Dunkel Medium Kontrast", "🌚"),
    DARK_HIGH("Dunkel Hoher Kontrast", "🌑"),
    SYSTEM("System", "🔄")
}