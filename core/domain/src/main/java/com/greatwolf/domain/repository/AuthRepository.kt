package com.greatwolf.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(email: String, password: String): Flow<Unit>
}