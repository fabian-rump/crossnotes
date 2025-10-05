package de.fabianrump.crossnotes.data.remote.provider

import de.fabianrump.crossnotes.BuildConfig

actual object SecretProvider {
    actual val apiKey: String = BuildConfig.API_KEY
}