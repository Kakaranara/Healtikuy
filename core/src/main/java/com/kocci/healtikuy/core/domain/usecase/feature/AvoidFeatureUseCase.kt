package com.kocci.healtikuy.core.domain.usecase.feature

import com.kocci.healtikuy.core.domain.model.AvoidFeature
import kotlinx.coroutines.flow.Flow

interface AvoidFeatureUseCase {
    fun getData() : Flow<AvoidFeature>
    fun isCheckable() : Boolean
    fun neverDoneItListener() : Flow<Boolean>
    suspend fun changeNeverDone(state: Boolean)
    suspend fun checkAllForToday(obj: AvoidFeature)
    suspend fun imNotDrinkAlcohol(obj : AvoidFeature)
    suspend fun imNotSmoking(obj : AvoidFeature)
    suspend fun iLimitMySugar(obj : AvoidFeature)
    suspend fun iLimitMyOil(obj : AvoidFeature)
}