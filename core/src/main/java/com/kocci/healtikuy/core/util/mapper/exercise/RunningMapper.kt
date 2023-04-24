package com.kocci.healtikuy.core.util.mapper.exercise

import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity
import com.kocci.healtikuy.core.domain.model.exercise.Running

fun Running.toEntity(): RunningEntity {
    return RunningEntity(
        id = this.id,
        duration = this.duration,
        distance = this.distance,
        isCompleted = this.isCompleted,
        timeCompleted = this.timeCompleted,
        timeStamp = this.timeStamp
    )
}

fun RunningEntity.toDomain(): Running {
    return Running(
        id = this.id,
        duration = this.duration,
        distance = this.distance,
        isCompleted = this.isCompleted,
        timeCompleted = this.timeCompleted,
        timeStamp = this.timeStamp
    )
}