package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.WaterIntake
import kotlinx.coroutines.flow.Flow

interface WaterIntakeUseCase {
    fun getWaterIntakeData(): Flow<WaterIntake>
    suspend fun insertNewData(waterIntake: WaterIntake)
    suspend fun updateQuantity(waterIntake: WaterIntake)
    suspend fun completeMission(waterIntake: WaterIntake)
}