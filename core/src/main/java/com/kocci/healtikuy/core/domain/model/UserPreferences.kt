package com.kocci.healtikuy.core.domain.model

data class UserPreferences(
    val lastLogin: Long,
    val points: Long,
    val coin: Int,
    val username: String,
    val email: String,
    val photoUrl: String,
)