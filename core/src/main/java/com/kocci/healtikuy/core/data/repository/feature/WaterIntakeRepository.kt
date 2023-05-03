package com.kocci.healtikuy.core.data.repository.feature

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.repository.IWaterIntakeRepository
import com.kocci.healtikuy.core.util.helper.PointsManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WaterIntakeRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferenceManager: UserPreferencesManager
) : IWaterIntakeRepository {
    override fun getLatestWaterIntakeData(): Flow<WaterIntakeEntity?> =
        localDataSource.getWaterIntakeLastRow()

    override suspend fun insert(entity: WaterIntakeEntity) =
        localDataSource.insertWaterIntake(entity)

    override suspend fun update(entity: WaterIntakeEntity) =
        localDataSource.updateWaterIntake(entity)

    override suspend fun updateAndAddPoints(entity: WaterIntakeEntity) {
        localDataSource.updateWaterIntake(entity)
        preferenceManager.addPoints(PointsManager.WATER_INTAKE_POINT)
    }
}