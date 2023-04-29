package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

interface TimeBasedFeatureUseCase<T> {
    suspend fun getAllData(): List<T>
    fun getSleepProgress(): Flow<T>
    fun getSchedule(): Flow<TimeIndicator>
    fun isTheTimeWithin1Hours(time: Long): Boolean
    fun showFormattedSetTime(time: Long): String
    fun setScheduleForNotification(sleep: Sleep, time: Long)

    suspend fun setSchedule(time: Long)
    suspend fun insertNewData(sleep: Sleep)
    suspend fun completeMission(sleep: Sleep)
}

sealed class TimeIndicator(val time: Long) {
    class Set(data: Long) : TimeIndicator(data)
    object NotSet : TimeIndicator(0)
}

