package com.greatwolf.domain.di

import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.domain.usecase.SignUpUseCase
import com.greatwolf.domain.usecase.SignUpVerificationResendUseCase
import com.greatwolf.domain.usecase.SignUpVerificationUseCase
import com.greatwolf.domain.usecase.ValidatePasswordUseCase
import org.koin.dsl.module

val domainModule = module {
    single<ValidatePasswordUseCase> {
        ValidatePasswordUseCase()
    }
    single<SignUpUseCase> {
        SignUpUseCase(get<AuthRepository>())
    }
    single<SignUpVerificationUseCase> {
        SignUpVerificationUseCase(get<AuthRepository>())
    }
    single<SignUpVerificationResendUseCase> {
        SignUpVerificationResendUseCase(get<AuthRepository>())
    }
}