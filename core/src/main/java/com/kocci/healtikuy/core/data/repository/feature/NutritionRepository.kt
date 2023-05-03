package com.kocci.healtikuy.core.data.repository.feature

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.NutritionEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.repository.INutritionRepository
import com.kocci.healtikuy.core.util.helper.PointsManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NutritionRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferencesManager: UserPreferencesManager
) : INutritionRepository {
    override fun getTodaysData(): Flow<List<NutritionEntity>> {
        return localDataSource.getNutritionData()
    }

    override suspend fun insertNewData(entity: NutritionEntity) {
        localDataSource.insertNutritionData(entity)
        preferencesManager.addPoints(PointsManager.NUTRITION_POINT)
    }
}