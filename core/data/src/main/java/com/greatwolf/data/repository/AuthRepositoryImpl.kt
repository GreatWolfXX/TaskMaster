package com.greatwolf.data.repository

import com.greatwolf.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    override fun signUp(
        email: String,
        password: String
    ): Flow<Unit> = flow {
        supabaseClient.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        emit(Unit)
    }

    override fun signUpOtpVerification(
        email: String,
        otp: String
    ): Flow<Unit> = flow {
        supabaseClient.auth.verifyEmailOtp(
            type = OtpType.Email.SIGNUP,
            email = email,
            token = otp
        )
        emit(Unit)
    }

    override fun signUpOtpVerificationResend(email: String): Flow<Unit> = flow {
        supabaseClient.auth.resendEmail(
            type = OtpType.Email.SIGNUP,
            email = email
        )
        emit(Unit)
    }
}