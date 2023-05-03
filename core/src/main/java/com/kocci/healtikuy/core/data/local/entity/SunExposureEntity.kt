package com.kocci.healtikuy.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("sun_exposure")
data class SunExposureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeStamp: Long = System.currentTimeMillis(),
    val timeCompleted: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)