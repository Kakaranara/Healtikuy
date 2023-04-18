package com.kocci.healtikuy.core.data.local.entity.exercise

import androidx.room.Entity

@Entity(tableName = "StaticBike")
data class StaticBikeEntity(
    var set: Int,
    var restTime: Int,
    var interval: Int,
    var isCompleted: Boolean = false,
    var timeStamp: Long
)
