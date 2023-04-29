package com.kocci.healtikuy.core.domain.model

data class Challenge(
    val challengeId : String,
    val name: String,
    val coinRewards: Int,
    var isCompleted: Boolean = false,
    val category: String,
    val pointRequired : Int = 0,
    val loginRequired : Int = 0
)