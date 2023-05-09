package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.NutritionEntity
import kotlinx.coroutines.flow.Flow

interface INutritionRepository {
    fun getTodaysData(): Flow<List<NutritionEntity>>
    suspend fun insertNewData(entity: NutritionEntity)

    suspend fun getAllData() : List<NutritionEntity>
    suspend fun deleteHistory()
}