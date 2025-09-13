package com.greatwolf.models

data class Profile(
    val id: String,
    val fullName: String?,
    val userName: String?,
    val dataOfBirth: String?,
    val email: String,
    val phone: String?,
    val imageUrl: String?
)

