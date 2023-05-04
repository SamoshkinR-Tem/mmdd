package com.artsam.mmdd

import android.app.Application
import apis
import com.artsam.mmdd.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import repo
import timber.log.Timber

class MukundaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeLogging()
        initializeDependencies()
    }

    private fun initializeLogging() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initializeDependencies() {
        startKoin {
            androidContext(this@MukundaApp)
            modules(
                apis,
                repo,
                viewModelModule,
            )
        }
    }
}
