package com.greatwolf.models

data class Project(
    val id: String,
    val priority: Int, // 0-2
    val title: String,
    val description: String,
    val progress: Int, // 0 - 100%
    val date: String,
    val color: Long
)

