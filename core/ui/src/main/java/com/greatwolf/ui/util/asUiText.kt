package com.greatwolf.ui.util

import com.greatwolf.common.Error
import com.greatwolf.ui.R

fun Error.EmailError.asUiText(): UiText = when (this) {
    Error.EmailError.NO_VALID -> UiText.StringResource(
        R.string.err_no_valid
    )
}

fun Error.PasswordError.asUiText(): UiText = when (this) {
    Error.PasswordError.TOO_SHORT -> UiText.StringResource(
        R.string.err_too_short
    )

    Error.PasswordError.NO_UPPERCASE -> UiText.StringResource(
        R.string.err_no_uppercase
    )

    Error.PasswordError.NO_LETTER -> UiText.StringResource(
        R.string.err_no_letter
    )

    Error.PasswordError.NO_DIGIT -> UiText.StringResource(
        R.string.err_no_digit
    )

    Error.PasswordError.REPEAT_PASSWORD_NO_MATCH -> UiText.StringResource(
        R.string.err_repeat_password_no_match
    )
}