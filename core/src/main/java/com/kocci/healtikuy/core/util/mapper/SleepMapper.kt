package com.kocci.healtikuy.core.util.mapper

import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.model.Sleep

fun Sleep.toEntity() = SleepEntity(
    id = this.id,
    timeStamp = this.timeStamp,
    isCompleted = this.isCompleted,
    timeCompleted = this.timeCompleted
)

fun SleepEntity.toDomain() = Sleep(
    id = this.id,
    timeStamp = this.timeStamp,
    isCompleted = this.isCompleted,
    timeCompleted = this.timeCompleted

)