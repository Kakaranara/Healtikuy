package com.kocci.healtikuy.core.domain.model

data class Sleep(
    val id: Int = 0,
    val timeStamp: Long = System.currentTimeMillis(),
    var isCompleted: Boolean = false
)
