package com.kocci.healtikuy.core.domain.model.exercise

data class StaticBike(
    var id: Int = 0,
    val set: Int = 0,
    val restTime: Int = 0,
    val interval: Int = 0,
    var isCompleted: Boolean = false,
    var timeCompleted: Long = System.currentTimeMillis(),
    var timeStamp: Long = System.currentTimeMillis()
)
