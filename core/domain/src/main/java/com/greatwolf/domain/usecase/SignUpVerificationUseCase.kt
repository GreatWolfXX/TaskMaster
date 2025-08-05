package com.greatwolf.domain.usecase

import com.greatwolf.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpVerificationUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, otp: String): Flow<Unit> = flow {
        authRepository.signUpOtpVerification(email, otp).collect { value ->
            emit(value)
        }
    }
}