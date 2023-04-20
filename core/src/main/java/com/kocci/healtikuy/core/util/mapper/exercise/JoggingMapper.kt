package com.kocci.healtikuy.core.util.mapper.exercise

import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import com.kocci.healtikuy.core.domain.model.exercise.Jogging

fun Jogging.toEntity(): JoggingEntity {
    return JoggingEntity(
        id = this.id,
        duration = this.duration,
        distance = this.distance,
        isCompleted = this.isCompleted,
        timeStamp = this.timeStamp
    )
}

fun JoggingEntity.toDomain() : Jogging{
    return Jogging(
        id = this.id,
        duration = this.duration,
        distance = this.distance,
        isCompleted = this.isCompleted,
        timeStamp = this.timeStamp
    )
}