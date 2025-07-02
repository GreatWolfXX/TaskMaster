package com.greatwolf.auth.di

import com.greatwolf.auth.create.SignUpViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::SignUpViewModel)
}