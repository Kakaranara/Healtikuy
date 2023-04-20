package com.kocci.healtikuy.core.domain.model.exercise

data class Jogging(
    val id: Int = 0,
    var duration: Int = 0,
    var distance: Int = 0,
    var isCompleted: Boolean = false,
    var timeStamp: Long = System.currentTimeMillis()
)
