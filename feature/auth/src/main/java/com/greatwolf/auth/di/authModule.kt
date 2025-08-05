package com.greatwolf.auth.di

import com.greatwolf.auth.create.SignUpViewModel
import com.greatwolf.auth.login.SignInViewModel
import com.greatwolf.auth.password.NewPasswordViewModel
import com.greatwolf.auth.password.ResetPasswordViewModel
import com.greatwolf.auth.profile.ProfileViewModel
import com.greatwolf.auth.verification.VerificationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::NewPasswordViewModel)
    viewModelOf(::ResetPasswordViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::VerificationViewModel)
}