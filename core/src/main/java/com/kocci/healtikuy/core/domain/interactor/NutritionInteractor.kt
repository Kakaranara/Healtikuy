package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.NutritionRepository
import com.kocci.healtikuy.core.domain.model.Nutrition
import com.kocci.healtikuy.core.domain.repository.INutritionRepository
import com.kocci.healtikuy.core.domain.usecase.nutrition.NutritionUseCase
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NutritionInteractor @Inject constructor(
    private val repository: NutritionRepository
) : NutritionUseCase {
    override fun getNutritionFood(): Flow<List<Nutrition>> {
        return repository.getTodaysData().map { it.map { it.toDomain() } }
    }

    override suspend fun addFood(nutrition: Nutrition) {
        repository.insertNewData(nutrition.toEntity())
    }
}