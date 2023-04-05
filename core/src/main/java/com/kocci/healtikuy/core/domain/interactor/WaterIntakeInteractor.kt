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
                insertNewData(waterIntake)
                waterIntake
            } else if (!DateHelper.isToday(it.timeStamp)) {
                val waterIntake = WaterIntake(quantity = 0, timeStamp = System.currentTimeMillis())
                insertNewData(waterIntake)
                waterIntake
            } else {
                it.toDomain()
            }
        }
    }

    override suspend fun insertNewData(waterIntake: WaterIntake) {
        repository.insert(waterIntake.toEntity())
    }

    override suspend fun updateQuantity(waterIntake: WaterIntake) {
        waterIntake.quantity += 1
        repository.update(waterIntake.toEntity())
    }

    override suspend fun completeMission(waterIntake: WaterIntake) {
        waterIntake.isCompleted = true
        repository.updateAndAddPoints(waterIntake.toEntity())
    }
}