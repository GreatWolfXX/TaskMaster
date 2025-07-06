package com.greatwolf.domain.usecase

import com.greatwolf.common.Error
import com.greatwolf.common.Result
import com.greatwolf.common.constant.MIN_PASSWORD_LENGTH
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Flow<Result<Unit, Error.PasswordError>> = flow {
        if (password.length < MIN_PASSWORD_LENGTH) {
            emit(Result.Error(Error.PasswordError.TOO_SHORT))
            return@flow
        }
        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            emit(Result.Error(Error.PasswordError.NO_DIGIT))
            return@flow
        }
        val hasLetter = password.any { it.isLetter() }
        if (!hasLetter) {
            emit(Result.Error(Error.PasswordError.NO_LETTER))
            return@flow
        }
        val hasUppercaseChar = password.any { it.isUpperCase() }
        if (!hasUppercaseChar) {
            emit(Result.Error(Error.PasswordError.NO_UPPERCASE))
            return@flow
        }
        emit(Result.Success(Unit))
    }
}