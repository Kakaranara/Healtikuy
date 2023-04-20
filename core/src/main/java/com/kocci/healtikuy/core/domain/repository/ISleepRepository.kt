package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.usecase.SleepIndicator
import kotlinx.coroutines.flow.Flow

interface ISleepRepository {
    fun getLatestData(): Flow<SleepEntity?>
    fun getSchedule(): Flow<SleepIndicator>
    suspend fun insertData(entity: SleepEntity)
    suspend fun updateData(entity: SleepEntity)
    suspend fun updateAndAddPoints(entity: SleepEntity)
    suspend fun changeSchedule(time: Long)
}