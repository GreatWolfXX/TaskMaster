package com.greatwolf.ui.util

import com.greatwolf.common.InputError
import com.greatwolf.ui.R

fun InputError.Email.asUiText(): UiText = when (this) {
    InputError.Email.NO_VALID -> UiText.StringResource(
        R.string.err_no_valid
    )
}

fun InputError.Password.asUiText(): UiText = when (this) {
    InputError.Password.TOO_SHORT -> UiText.StringResource(
        R.string.err_too_short
    )

    InputError.Password.NO_UPPERCASE -> UiText.StringResource(
        R.string.err_no_uppercase
    )

    InputError.Password.NO_LETTER -> UiText.StringResource(
        R.string.err_no_letter
    )

    InputError.Password.NO_DIGIT -> UiText.StringResource(
        R.string.err_no_digit
    )

    InputError.Password.REPEAT_PASSWORD_NO_MATCH -> UiText.StringResource(
        R.string.err_repeat_password_no_match
    )
}