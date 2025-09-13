package com.greatwolf.data.repository

import com.greatwolf.common.DataError
import com.greatwolf.common.Result
import com.greatwolf.common.asResult
import com.greatwolf.data.constant.PROFILES_ID_COLUMN
import com.greatwolf.data.constant.PROFILES_TABLE
import com.greatwolf.data.mapper.mapToDataError
import com.greatwolf.data.mapper.toDomain
import com.greatwolf.data.model.ProfileDto
import com.greatwolf.domain.repository.ProfileRepository
import com.greatwolf.models.Profile
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : ProfileRepository {
    override fun getProfileById(id: String): Flow<Result<Profile, DataError.Network>> = flow {
        val response = supabaseClient.postgrest.from(PROFILES_TABLE)
            .select {
                filter {
                    eq(PROFILES_ID_COLUMN, id)
                }
            }.decodeSingle<ProfileDto>()
        emit(response.toDomain())
    }.asResult(::mapToDataError)
}