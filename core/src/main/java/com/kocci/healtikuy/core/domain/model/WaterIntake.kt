package com.kocci.healtikuy.core.domain.model

data class WaterIntake(
    val id: Int = 0,
    var quantity: Int = 0,
    val timeStamp: Long = System.currentTimeMillis(),
    var isCompleted: Boolean = false
)
