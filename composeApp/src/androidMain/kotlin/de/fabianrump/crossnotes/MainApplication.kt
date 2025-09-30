package de.fabianrump.crossnotes

import android.app.Application
import de.fabianrump.crossnotes.di.initKoin
import de.fabianrump.crossnotes.logging.setupLogging
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLogging()
        initKoin(
            appDeclaration = { androidContext(this@MainApplication) },
        )
    }
}