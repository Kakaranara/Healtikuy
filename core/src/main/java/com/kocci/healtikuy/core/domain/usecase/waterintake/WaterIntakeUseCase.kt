package com.kocci.healtikuy.core.domain.usecase.waterintake

import com.kocci.healtikuy.core.domain.model.WaterIntake
import kotlinx.coroutines.flow.Flow

interface WaterIntakeUseCase {
    fun getWaterIntakeData(): Flow<WaterIntake>
    fun setAlarm(isGoalsReached: Boolean)
    fun cancelAlarm()

    suspend fun insertNewData(waterIntake: WaterIntake)
    suspend fun updateQuantity(waterIntake: WaterIntake)
    suspend fun completeMission(waterIntake: WaterIntake)
}