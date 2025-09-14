package com.greatwolf.domain.repository

import com.greatwolf.common.DataError
import com.greatwolf.common.Result
import com.greatwolf.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileById(id: String): Flow<Result<Profile, DataError.Network>>
}