package com.brasileirao

import android.app.Application
import com.brasileirao.di.brasileiraoModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BrasileiraoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@BrasileiraoApplication)
            modules(brasileiraoModule)
        }
    }
}
