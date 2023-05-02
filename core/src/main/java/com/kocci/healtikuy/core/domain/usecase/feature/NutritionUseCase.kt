package com.kocci.healtikuy.core.domain.usecase.feature

import com.kocci.healtikuy.core.domain.model.Nutrition
import kotlinx.coroutines.flow.Flow

interface NutritionUseCase {
    fun getNutritionFood(): Flow<List<Nutrition>>
    suspend fun addFood(nutrition: Nutrition)
}