package com.greatwolf.domain.usecase

import com.greatwolf.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Unit> = flow {
        authRepository.signUp(email, password).collect { value ->
            emit(value)
        }
    }
}