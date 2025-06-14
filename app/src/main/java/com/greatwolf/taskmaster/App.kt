package com.greatwolf.taskmaster

import android.app.Application
import com.greatwolf.onboarding.di.onboardingModule
import com.greatwolf.taskmaster.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                onboardingModule
            )
        }
    }
}