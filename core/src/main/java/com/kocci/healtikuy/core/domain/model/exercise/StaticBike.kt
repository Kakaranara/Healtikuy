package com.kocci.healtikuy.core.domain.model.exercise

data class StaticBike(
    var id: Int = 0,
    var set: Int = 0,
    var restTime: Int = 0,
    var interval: Int = 0,
    var isCompleted: Boolean = false,
    var timeCompleted: Long = System.currentTimeMillis(),
    var timeStamp: Long = System.currentTimeMillis()
)
