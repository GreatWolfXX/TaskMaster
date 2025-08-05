package com.greatwolf.domain.usecase

import com.greatwolf.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpVerificationResendUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Unit> = flow {
        authRepository.signUpOtpVerificationResend(email).collect { value ->
            emit(value)
        }
    }
}