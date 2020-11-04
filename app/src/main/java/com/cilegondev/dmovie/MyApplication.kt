package com.cilegondev.dmovie

import android.app.Application
import com.cilegondev.dmovie.core.di.databaseModule
import com.cilegondev.dmovie.core.di.networkModule
import com.cilegondev.dmovie.core.di.repositoryModule
import com.cilegondev.dmovie.di.useCaseModule
import com.cilegondev.dmovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
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