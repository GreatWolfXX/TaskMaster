package com.greatwolf.domain.repository

import com.greatwolf.common.DataError
import com.greatwolf.common.Result
import com.greatwolf.models.Profile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun currentSession(): Flow<Result<Profile?, DataError.Network>>

    fun signUp(email: String, password: String): Flow<Result<Unit, DataError.Network>>
    fun signUpOtpVerification(email: String, otp: String): Flow<Result<Unit, DataError.Network>>
    fun signUpOtpVerificationResend(email: String): Flow<Result<Unit, DataError.Network>>

    fun signIn(email: String, password: String): Flow<Result<Unit, DataError.Network>>
    fun signOut(): Flow<Result<Unit, DataError.Network>>
}