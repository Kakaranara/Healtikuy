package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

sealed class SleepIndicator(val sleepTime: Long) {
    class Set(data: Long) : SleepIndicator(data)
    object NotSet : SleepIndicator(0)
}

interface SleepUseCase {
    suspend fun getAllData(): List<Sleep>
    fun getSleepProgress(): Flow<Sleep>
    fun getSchedule(): Flow<SleepIndicator>
    fun isTheTimeWithin1Hours(time: Long): Boolean
    fun showFormattedSetTime(time: Long): String
    fun setScheduleForNotification(sleep: Sleep, time: Long)

    suspend fun setSchedule(time: Long)
    suspend fun insertNewData(sleep: Sleep)
    suspend fun completeMission(sleep: Sleep)
}