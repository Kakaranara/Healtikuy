package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

sealed class SleepIndicator(val data: Long) {
    class Set(data: Long) : SleepIndicator(data)
    object NotSet : SleepIndicator(0)
}

interface SleepUseCase {
    fun getSleepData(): Flow<Sleep>

    //    fun getSetTime(): Flow<Long?>
    fun getSetTime(): Flow<SleepIndicator>

    fun isTheTimeWithin1Hours(time: Long): Boolean

    fun showFormattedSetTime(time: Long): String

    suspend fun changeSetTime(time: Long)
    suspend fun insertNewData(sleep: Sleep)
    suspend fun updateNewData(sleep: Sleep)
}