package com.bygen.mofeedb

import android.app.Application
import com.bygen.mofeedb.core.di.databaseModule
import com.bygen.mofeedb.core.di.networkModule
import com.bygen.mofeedb.core.di.repositoryModule
import com.bygen.mofeedb.presentation.di.useCaseModule
import com.bygen.mofeedb.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinStarter: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@KoinStarter)
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