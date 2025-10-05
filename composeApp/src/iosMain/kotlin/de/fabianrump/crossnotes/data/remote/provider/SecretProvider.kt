package de.fabianrump.crossnotes.data.remote.provider

import platform.Foundation.NSProcessInfo

actual object SecretProvider {
    actual val apiKey: String = NSProcessInfo.processInfo.environment["API_KEY"].toString()
}