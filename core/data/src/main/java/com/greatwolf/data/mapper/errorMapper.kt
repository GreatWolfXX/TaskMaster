package com.greatwolf.data.mapper

import com.greatwolf.common.DataError
import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException

fun mapAuthError(code: AuthErrorCode?) = when (code) {
    AuthErrorCode.EmailAddressInvalid -> DataError.Network.OVER_EMAIL_SEND_RATE_LIMIT
    else -> DataError.Network.UNKNOWN
}

fun mapToDataError(e: Throwable): DataError = when (e) {
    is AuthRestException -> mapAuthError(e.errorCode)

    else -> DataError.Network.UNKNOWN
}