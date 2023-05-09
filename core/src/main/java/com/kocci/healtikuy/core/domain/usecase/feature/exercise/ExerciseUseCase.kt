package com.kocci.healtikuy.core.domain.usecase.feature.exercise

import kotlinx.coroutines.flow.Flow

interface ExerciseUseCase<T> {
    fun getDataProgress(): Flow<T>
    suspend fun getAllData(): List<T>
    suspend fun insertNewData(data: T)
    suspend fun completeMission(data: T)
    suspend fun clearHistory()
}


