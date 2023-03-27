package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.WaterIntake
import kotlinx.coroutines.flow.Flow

interface WaterIntakeUseCase {
    fun getWaterIntakeData(): Flow<WaterIntake>
    suspend fun insertData(waterIntake: WaterIntake)
    suspend fun updateData(waterIntake: WaterIntake)
    suspend fun updatePoint(waterIntake: WaterIntake)
}