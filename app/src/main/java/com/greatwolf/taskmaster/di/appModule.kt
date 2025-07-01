package com.greatwolf.taskmaster.di

import com.greatwolf.taskmaster.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SplashViewModel)
}