package com.kocci.healtikuy.core.domain.model

data class SunExposure(
    val id: Int = 0,
    val timeStamp: Long = System.currentTimeMillis(),
    var isCompleted: Boolean = false,
    var timeCompleted: Long = System.currentTimeMillis()
)