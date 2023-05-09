package com.kocci.healtikuy.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kocci.healtikuy.core.domain.model.Nutrition
import com.kocci.healtikuy.core.util.helper.DateHelper

@Entity("nutrition")
data class NutritionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodName: String,
    val category: String,
    val unixTimestamp: Long = DateHelper.getUnixEpoch(),
)
