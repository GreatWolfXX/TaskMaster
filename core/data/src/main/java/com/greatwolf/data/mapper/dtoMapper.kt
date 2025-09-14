package com.greatwolf.data.mapper

import com.greatwolf.data.model.ProfileDto
import com.greatwolf.models.Profile
import io.github.jan.supabase.auth.user.UserInfo

fun UserInfo.toDto() =
    ProfileDto(
        id = id,
        email = email,
        phone = phone,
    )

fun ProfileDto.toDomain() =
    Profile(
        id = id,
        fullName = fullName,
        userName = userName,
        dataOfBirth = dataOfBirth,
        email = email.orEmpty(),
        phone = phone,
        imageUrl = imageUrl
    )