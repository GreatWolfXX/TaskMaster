package com.greatwolf.taskmaster.koin

import com.greatwolf.taskmaster.ui.screen.onboarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModules = module {

}

val viewModelModules = module {
    viewModelOf(::OnboardingViewModel)
}