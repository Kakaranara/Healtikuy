package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import kotlinx.coroutines.flow.Flow

interface ISleepRepository {
    fun getLatestData(): Flow<SleepEntity?>
    suspend fun insertData(entity: SleepEntity)
    suspend fun updateData(entity: SleepEntity)
}