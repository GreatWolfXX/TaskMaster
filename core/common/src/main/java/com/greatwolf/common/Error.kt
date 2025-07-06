package com.greatwolf.common

sealed interface Error {
    enum class EmailError : Error {
        NO_VALID
    }

    enum class PasswordError : Error {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_LETTER,
        NO_DIGIT,
        REPEAT_PASSWORD_NO_MATCH
    }

    data class UnknownError(val exception: Throwable) : Error
}

fun Throwable.toError(): Error = when (this) {
    else -> Error.UnknownError(this)
}
