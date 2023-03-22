package com.kocci.healtikuy.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quantity: Int,
    val timeStamp: Long
)