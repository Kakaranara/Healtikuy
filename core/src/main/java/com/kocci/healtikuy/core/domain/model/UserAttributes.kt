package com.kocci.healtikuy.core.domain.model

data class UserAttributes(
    var lastLogin: Long,
    var points: Long,
    var coin: Int,
    var avatar: Set<String>
)