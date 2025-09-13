package com.greatwolf.data.model

data class ProfileDto (
    val id: String,
    val fullName: String? = null,
    val userName: String? = null,
    val dataOfBirth: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val imageUrl: String? = null
)
