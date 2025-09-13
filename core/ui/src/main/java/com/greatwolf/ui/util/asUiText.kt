package com.greatwolf.ui.util

import com.greatwolf.common.DataError
import com.greatwolf.common.InputError
import com.greatwolf.ui.R
import com.greatwolf.ui.util.UiText.*

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

fun DataError.Network.asUiText(): UiText = when (this) {
    DataError.Network.EMAIL_ADDRESS_INVALID -> StringResource(
        R.string.err_invalid_email_format
    )

    DataError.Network.OVER_EMAIL_SEND_RATE_LIMIT -> StringResource(
        R.string.err_over_email_send_rate_limit
    )

    DataError.Network.INVALID_CREDENTIALS -> StringResource(
        R.string.err_invalid_credentials
    )

    DataError.Network.UNKNOWN -> StringResource(
        R.string.err_unknown
    )
}