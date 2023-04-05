package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import kotlinx.coroutines.flow.Flow

interface IWaterIntakeRepository {
    fun getLatestWaterIntakeData(): Flow<WaterIntakeEntity?>
    suspend fun insert(entity: WaterIntakeEntity)
    suspend fun update(entity: WaterIntakeEntity)
    suspend fun updateAndAddPoints(entity: WaterIntakeEntity)
}