package com.kocci.healtikuy.core.data.local.entity.exercise

import androidx.room.Entity

@Entity(tableName = "RunningEntity")
data class RunningEntity(
    var duration: Int,
    var distance: Int,
    var isCompleted: Boolean = false,
    var timeStamp: Long

)
