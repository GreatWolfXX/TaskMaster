package com.greatwolf.common

sealed interface Error {
    data class Unknown(val e: Throwable) : DataError
}

sealed interface InputError : Error {
    enum class Email : InputError {
        NO_VALID
    }

    enum class Password : InputError {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_LETTER,
        NO_DIGIT,
        REPEAT_PASSWORD_NO_MATCH
    }
}

sealed interface DataError : Error {
    enum class Network : DataError {
        EMAIL_ADDRESS_INVALID,
        OVER_EMAIL_SEND_RATE_LIMIT,
        INVALID_CREDENTIALS,
        UNKNOWN
    }
}