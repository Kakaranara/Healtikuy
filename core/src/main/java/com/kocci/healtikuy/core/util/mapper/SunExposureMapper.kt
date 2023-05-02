package com.kocci.healtikuy.core.util.mapper

import com.kocci.healtikuy.core.data.local.entity.SunExposureEntity
import com.kocci.healtikuy.core.domain.model.SunExposure


fun SunExposure.toEntity() = SunExposureEntity(
    id = this.id,
    timeStamp = this.timeStamp,
    isCompleted = this.isCompleted,
    timeCompleted = this.timeCompleted
)

fun SunExposureEntity.toDomain() = SunExposure(
    id = this.id,
    timeStamp = this.timeStamp,
    isCompleted = this.isCompleted,
    timeCompleted = this.timeCompleted

)