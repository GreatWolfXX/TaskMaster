package com.greatwolf.data.repository

import com.greatwolf.common.DataError
import com.greatwolf.common.Result
import com.greatwolf.common.asResult
import com.greatwolf.data.mapper.mapToDataError
import com.greatwolf.data.mapper.toDomain
import com.greatwolf.data.mapper.toDto
import com.greatwolf.domain.repository.AuthRepository
import com.greatwolf.models.Profile
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : AuthRepository {
    override fun currentSession(): Flow<Result<Profile?, DataError.Network>> = flow {
        val response = supabaseClient.auth.currentSessionOrNull()?.user?.toDto()
        emit(response?.toDomain())
    }.asResult(::mapToDataError)

    override fun signUp(
        email: String,
        password: String
    ): Flow<Result<Unit, DataError.Network>> = flow {
        supabaseClient.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        emit(Unit)
    }.asResult(::mapToDataError)

    override fun signUpOtpVerification(
        email: String,
        otp: String
    ): Flow<Result<Unit, DataError.Network>> = flow {
        supabaseClient.auth.verifyEmailOtp(
            type = OtpType.Email.SIGNUP,
            email = email,
            token = otp
        )
        emit(Unit)
    }.asResult(::mapToDataError)

    override fun signUpOtpVerificationResend(email: String): Flow<Result<Unit, DataError.Network>> =
        flow {
            val response = supabaseClient.auth.resendEmail(
                type = OtpType.Email.SIGNUP,
                email = email
            )
            emit(response)
        }.asResult(::mapToDataError)

    override fun signIn(
        email: String,
        password: String
    ): Flow<Result<Unit, DataError.Network>> = flow {
        val response = supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
        emit(response)
    }.asResult(::mapToDataError)

    override fun signOut(): Flow<Result<Unit, DataError.Network>> = flow {
        supabaseClient.auth.signOut()
        emit(Unit)
    }.asResult(::mapToDataError)
}