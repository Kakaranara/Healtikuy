package com.kocci.healtikuy.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("avoid_feature")
data class AvoidFeatureEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var alcohol: Boolean = false,
    var smoke: Boolean = false,
    var limitSugar : Boolean = false,
    var limitFat : Boolean = false,
    var timeStamp: Long = System.currentTimeMillis(),
)