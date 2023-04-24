package com.kocci.healtikuy.core.data.local.entity.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "static_bike")
data class StaticBikeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var set: Int,
    var restTime: Int,
    var interval: Int,
    var isCompleted: Boolean = false,
    var timeCompleted: Long,
    var timeStamp: Long
)
