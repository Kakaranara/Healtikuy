package com.kocci.healtikuy.core.domain.model

data class WaterIntake(
    val id: Int = 0,
    var quantity: Int,
    val timeStamp: Long,
    var isCompleted: Boolean = false
)
