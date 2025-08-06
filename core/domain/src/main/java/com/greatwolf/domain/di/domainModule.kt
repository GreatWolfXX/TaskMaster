package com.greatwolf.domain.di

import com.greatwolf.domain.usecase.ValidatePasswordUseCase
import org.koin.dsl.module

val domainModule = module {
    single<ValidatePasswordUseCase> {
        ValidatePasswordUseCase()
    }
}