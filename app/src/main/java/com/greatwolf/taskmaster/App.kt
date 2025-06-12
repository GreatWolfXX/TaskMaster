package com.greatwolf.taskmaster

import android.app.Application
import com.greatwolf.taskmaster.koin.appModules
import com.greatwolf.taskmaster.koin.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModules,
                viewModelModules
            )
        }
    }
}