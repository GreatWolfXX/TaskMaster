package com.greatwolf.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class OnboardingPages(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    data class First(val isSystemDark: Boolean) : OnboardingPages(
        image = if (isSystemDark) R.drawable.onboarding_1_dark else R.drawable.onboarding_1_light,
        title = R.string.onboarding_title_1,
        description = R.string.onboarding_desc_1
    )

    data class Second(val isSystemDark: Boolean) : OnboardingPages(
        image = if (isSystemDark) R.drawable.onboarding_2_dark else R.drawable.onboarding_2_light,
        title = R.string.onboarding_title_2,
        description = R.string.onboarding_desc_2
    )

    data class Third(val isSystemDark: Boolean) : OnboardingPages(
        image = if (isSystemDark) R.drawable.onboarding_3_dark else R.drawable.onboarding_3_light,
        title = R.string.onboarding_title_3,
        description = R.string.onboarding_desc_3
    )

    data object Fourth : OnboardingPages(
        image = R.drawable.onboarding_4,
        title = R.string.onboarding_title_4,
        description = R.string.onboarding_desc_4
    )
}