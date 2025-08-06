package com.greatwolf.domain.repository

import com.greatwolf.common.DataError
import com.greatwolf.common.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(email: String, password: String): Flow<Result<Unit, DataError>>
    fun signUpOtpVerification(email: String, otp: String): Flow<Result<Unit, DataError>>
    fun signUpOtpVerificationResend(email: String): Flow<Result<Unit, DataError>>

}