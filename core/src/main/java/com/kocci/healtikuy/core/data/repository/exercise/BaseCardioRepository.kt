package com.kocci.healtikuy.core.data.repository.exercise

import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseTimeIndicator
import kotlinx.coroutines.flow.Flow

abstract class BaseCardioRepository<EntityType> {
    abstract suspend fun getAllData(): List<EntityType>
    abstract fun getLatestData(): Flow<EntityType?>
    abstract fun getSchedule(): Flow<ExerciseTimeIndicator>
    abstract suspend fun insertNewData(data: EntityType)
    abstract suspend fun updateData(data: EntityType)
    abstract suspend fun setSchedule(time: Long, interval: Int)
    abstract suspend fun editJoggingTime()
    abstract suspend fun updatePoints()
}