package com.kocci.healtikuy.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("WaterIntake")
data class WaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quantity: Int,
    val timeStamp: Long,
    var isCompleted: Boolean = false
)