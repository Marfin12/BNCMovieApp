package com.example.bncmovieapp

import android.app.Application
import com.example.bncmovieapp.core.di.databaseModule
import com.example.bncmovieapp.core.di.networkModule
import com.example.bncmovieapp.core.di.repositoryModule
import com.example.bncmovieapp.di.useCaseModule
import com.example.bncmovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}