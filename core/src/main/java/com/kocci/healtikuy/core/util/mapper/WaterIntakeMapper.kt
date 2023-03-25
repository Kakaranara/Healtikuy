package com.kocci.healtikuy.core.util.mapper

import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import com.kocci.healtikuy.core.domain.model.WaterIntake

fun WaterIntakeEntity.toDomain() = WaterIntake(
    id = this.id,
    quantity = this.quantity,
    timeStamp = this.timeStamp
)

fun WaterIntake.toEntity() = WaterIntakeEntity(
    id = this.id,
    quantity = this.quantity,
    timeStamp = this.timeStamp
)