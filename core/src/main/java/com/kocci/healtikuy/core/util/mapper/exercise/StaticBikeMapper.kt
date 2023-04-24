package com.kocci.healtikuy.core.util.mapper.exercise

import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity
import com.kocci.healtikuy.core.domain.model.exercise.StaticBike

fun StaticBike.toEntity(): StaticBikeEntity {
    return StaticBikeEntity(
        id = this.id,
        set = this.set,
        restTime = this.restTime,
        interval = this.interval,
        isCompleted = this.isCompleted,
        timeCompleted = this.timeCompleted,
        timeStamp = this.timeStamp
    )
}

fun StaticBikeEntity.toDomain(): StaticBike {
    return StaticBike(
        id = this.id,
        set = this.set,
        restTime = this.restTime,
        interval = this.interval,
        isCompleted = this.isCompleted,
        timeCompleted = this.timeCompleted,
        timeStamp = this.timeStamp
    )
}