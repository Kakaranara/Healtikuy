package com.kocci.healtikuy.core.domain.model.exercise

data class Running(
    var id: Int = 0,
    var duration: Int = 0,
    var distance: Int = 0,
    var isCompleted: Boolean = false,
    var timeCompleted: Long = System.currentTimeMillis(),
    var timeStamp: Long = System.currentTimeMillis()
)
