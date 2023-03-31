package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import kotlinx.coroutines.flow.Flow

interface IWaterIntakeRepository {
    fun getLatestWaterIntakeData(): Flow<WaterIntakeEntity?>

    suspend fun insertWaterIntake(entity: WaterIntakeEntity)

    suspend fun updateWaterIntake(entity: WaterIntakeEntity)
    suspend fun updateAndAddPoints(entity: WaterIntakeEntity)
}