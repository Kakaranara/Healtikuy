package com.kocci.healtikuy.core.domain.usecase.exercise

import kotlinx.coroutines.flow.Flow

interface ExerciseUseCase<T> {
    fun getDataProgress(): Flow<T>
    fun getAllData(): List<T>
    suspend fun insertNewData(data: T)

    suspend fun updateData(data: T)
}


