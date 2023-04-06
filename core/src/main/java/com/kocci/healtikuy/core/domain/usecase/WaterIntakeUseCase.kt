package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.WaterIntake
import kotlinx.coroutines.flow.Flow

interface WaterIntakeUseCase {
    fun getWaterIntakeData(): Flow<WaterIntake>
    fun setAlarm(isGoalsCompleted: Boolean)
    fun cancelAlarm()

    suspend fun insertNewData(waterIntake: WaterIntake)
    suspend fun updateQuantity(waterIntake: WaterIntake)
    suspend fun completeMission(waterIntake: WaterIntake)
}