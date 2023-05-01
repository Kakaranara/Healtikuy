package com.kocci.healtikuy.core.domain.repository.feature.timebased

import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.usecase.TimeIndicator
import kotlinx.coroutines.flow.Flow

interface ITimeFeatureRepository<Entity> {
    suspend fun getAllData(): List<Entity>
    fun getLatestData(): Flow<Entity?>
    fun getSchedule(): Flow<TimeIndicator>
    suspend fun insertData(entity: Entity)
    suspend fun updateData(entity: Entity)
    suspend fun updateAndAddPoints(entity: Entity)
    suspend fun changeSchedule(time: Long)
}