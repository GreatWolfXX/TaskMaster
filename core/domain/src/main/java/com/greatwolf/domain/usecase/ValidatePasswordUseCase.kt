package com.greatwolf.domain.usecase

import com.greatwolf.common.InputError
import com.greatwolf.common.Result
import com.greatwolf.common.constant.MIN_PASSWORD_LENGTH
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Flow<Result<Unit, InputError.Password>> = flow {
        if (password.length < MIN_PASSWORD_LENGTH) {
            emit(Result.Error(InputError.Password.TOO_SHORT))
            return@flow
        }
        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            emit(Result.Error(InputError.Password.NO_DIGIT))
            return@flow
        }
        val hasLetter = password.any { it.isLetter() }
        if (!hasLetter) {
            emit(Result.Error(InputError.Password.NO_LETTER))
            return@flow
        }
        val hasUppercaseChar = password.any { it.isUpperCase() }
        if (!hasUppercaseChar) {
            emit(Result.Error(InputError.Password.NO_UPPERCASE))
            return@flow
        }
        emit(Result.Success(Unit))
    }
}