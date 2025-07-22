package com.greatwolf.auth.di

import com.greatwolf.auth.create.SignUpViewModel
import com.greatwolf.auth.login.SignInViewModel
import com.greatwolf.auth.verification.VerificationViewModel
import com.greatwolf.domain.usecase.ValidatePasswordUseCase
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    single<ValidatePasswordUseCase> {
        ValidatePasswordUseCase()
    }

    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::VerificationViewModel)
}