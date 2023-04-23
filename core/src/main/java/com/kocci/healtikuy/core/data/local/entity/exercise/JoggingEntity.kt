package com.kocci.healtikuy.core.data.local.entity.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogging")
data class JoggingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var duration: Int,
    var distance: Int,
    var isCompleted: Boolean = false,
    var timeCompleted: Long,
    var timeStamp: Long
)