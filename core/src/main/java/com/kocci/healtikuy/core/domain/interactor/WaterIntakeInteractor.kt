package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.WaterIntakeRepository
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.core.domain.usecase.WaterIntakeUseCase
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WaterIntakeInteractor @Inject constructor(
    private val repository: WaterIntakeRepository,
) : WaterIntakeUseCase {
    override fun getWaterIntakeData(): Flow<WaterIntake> {
        return repository.getLatestWaterIntakeData().map {
            if (it == null) {
                val waterIntake = WaterIntake(quantity = 0, timeStamp = System.currentTimeMillis())
                insertData(waterIntake)
                waterIntake
            } else if (!DateHelper.isToday(it.timeStamp)) {
                val waterIntake = WaterIntake(quantity = 0, timeStamp = System.currentTimeMillis())
                insertData(waterIntake)
                waterIntake
            } else {
                it.toDomain()
            }
        }
    }

    override suspend fun insertData(waterIntake: WaterIntake) {
        repository.insertWaterIntake(waterIntake.toEntity())
    }

    override suspend fun updateData(waterIntake: WaterIntake) {
        waterIntake.quantity += 1
        repository.updateWaterIntake(waterIntake.toEntity())
    }

    override suspend fun completeMission(waterIntake: WaterIntake) {
        waterIntake.isCompleted = true
        repository.updateAndAddPoints(waterIntake.toEntity())
    }
}