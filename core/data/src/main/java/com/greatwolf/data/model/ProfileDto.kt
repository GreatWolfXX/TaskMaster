package com.greatwolf.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto (
    @SerialName("user_id")
    val id: String,
    @SerialName("full_name")
    val fullName: String? = null,
    @SerialName("user_name")
    val userName: String? = null,
    @SerialName("data_of_birth")
    val dataOfBirth: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null
)
