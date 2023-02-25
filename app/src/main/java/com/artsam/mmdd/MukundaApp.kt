package com.artsam.mmdd

import android.app.Application
import com.artsam.mmdd.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
                viewModelModule,
            )
        }
    }
}
