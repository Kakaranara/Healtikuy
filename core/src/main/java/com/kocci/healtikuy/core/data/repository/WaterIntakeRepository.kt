package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import com.kocci.healtikuy.core.domain.repository.IWaterIntakeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WaterIntakeRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : IWaterIntakeRepository {
    override fun getLatestWaterIntakeData(): Flow<WaterIntakeEntity?> =
        localDataSource.getWaterIntakeLastRow()

    override suspend fun insertWaterIntake(entity: WaterIntakeEntity) =
        localDataSource.insertWaterIntake(entity)

    override suspend fun updateWaterIntake(entity: WaterIntakeEntity) =
        localDataSource.updateWaterIntake(entity)
}