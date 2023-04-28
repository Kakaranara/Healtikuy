package com.kocci.healtikuy.core.domain.model

data class Challenges(
    val id: Int,
    val name: String,
    var isCompleted: Boolean
)