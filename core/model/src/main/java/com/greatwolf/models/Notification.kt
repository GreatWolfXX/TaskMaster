package com.greatwolf.models

data class Notification(
    val id: String,
    val type: Int, // 0-4
    val description: String,
    val date: String
)

