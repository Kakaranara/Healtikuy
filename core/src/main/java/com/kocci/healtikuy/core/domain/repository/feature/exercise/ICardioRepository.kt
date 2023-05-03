package com.kocci.healtikuy.core.domain.repository.feature.exercise

import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseTimeIndicator
import kotlinx.coroutines.flow.Flow

interface ICardioRepository<EntityType> {
    suspend fun getAllData(): List<EntityType>
    fun getLatestData(): Flow<EntityType?>
    fun getSchedule(): Flow<ExerciseTimeIndicator>
    suspend fun insertNewData(data: EntityType)
    suspend fun updateData(data: EntityType)
    suspend fun setSchedule(time: Long, interval: Int)
    suspend fun editSchedule()
    suspend fun updatePoints()
}