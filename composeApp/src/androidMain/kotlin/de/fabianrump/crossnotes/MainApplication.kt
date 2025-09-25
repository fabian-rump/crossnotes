package de.fabianrump.crossnotes

import android.app.Application
import de.fabianrump.crossnotes.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appDeclaration = { androidContext(this@MainApplication) },
        )
    }
}