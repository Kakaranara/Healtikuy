package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.AvoidFeature
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface AvoidFeatureUseCase {
    fun getData() : Flow<AvoidFeature>
    fun isCheckable() : Boolean
    suspend fun imNotDrinkAlcohol(obj : AvoidFeature)
    suspend fun imNotSmoking(obj : AvoidFeature)
    suspend fun iLimitMySugar(obj : AvoidFeature)
    suspend fun iLimitMyOil(obj : AvoidFeature)
}