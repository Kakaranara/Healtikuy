package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

interface SleepUseCase {
    fun setTime(time: Long)
    fun getSleepData(): Flow<Sleep?>
    suspend fun insertNewData(data: Sleep)
    suspend fun updateNewData(data: Sleep)
}